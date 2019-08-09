package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallOrderMapper;
import com.lzdn.aswxmall.db.dao.OrderMapper;
import com.lzdn.aswxmall.db.domain.AswxmallOrder;
import com.lzdn.aswxmall.db.domain.AswxmallOrderExample;
import com.lzdn.aswxmall.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class AswxmallOrderService {
    @Resource
    private AswxmallOrderMapper aswxmallOrderMapper;
    @Resource
    private OrderMapper orderMapper;

    public int add(AswxmallOrder order) {
        order.setAddTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        return aswxmallOrderMapper.insertSelective(order);
    }

    public int count(Integer userId) {
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) aswxmallOrderMapper.countByExample(example);
    }

    public AswxmallOrder findById(Integer orderId) {
        return aswxmallOrderMapper.selectByPrimaryKey(orderId);
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public int countByOrderSn(Integer userId, String orderSn) {
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return (int) aswxmallOrderMapper.countByExample(example);
    }

    // TODO 这里应该产生一个唯一的订单，但是实际上这里仍然存在两个订单相同的可能性
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(6);
        while (countByOrderSn(userId, orderSn) != 0) {
            orderSn = now + getRandomNum(6);
        }
        return orderSn;
    }

    public List<AswxmallOrder> queryByOrderStatus(Integer userId, List<Short> orderStatus, Integer page, Integer limit, String sort, String order) {
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.setOrderByClause(AswxmallOrder.Column.addTime.desc());
        AswxmallOrderExample.Criteria criteria = example.or();
        criteria.andUserIdEqualTo(userId);
        if (orderStatus != null) {
            criteria.andOrderStatusIn(orderStatus);
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return aswxmallOrderMapper.selectByExample(example);
    }

    public List<AswxmallOrder> querySelective(Integer userId, String orderSn, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        AswxmallOrderExample example = new AswxmallOrderExample();
        AswxmallOrderExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            criteria.andOrderSnEqualTo(orderSn);
        }
        if (orderStatusArray != null && orderStatusArray.size() != 0) {
            criteria.andOrderStatusIn(orderStatusArray);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return aswxmallOrderMapper.selectByExample(example);
    }

    public int updateWithOptimisticLocker(AswxmallOrder order) {
        LocalDateTime preUpdateTime = order.getUpdateTime();
        order.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateWithOptimisticLocker(preUpdateTime, order);
    }

    public void deleteById(Integer id) {
        aswxmallOrderMapper.logicalDeleteByPrimaryKey(id);
    }

    public int count() {
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andDeletedEqualTo(false);
        return (int) aswxmallOrderMapper.countByExample(example);
    }

    public List<AswxmallOrder> queryUnpaid(int minutes) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusMinutes(minutes);
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_CREATE).andAddTimeLessThan(expired).andDeletedEqualTo(false);
        return aswxmallOrderMapper.selectByExample(example);
    }

    public List<AswxmallOrder> queryUnconfirm(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_SHIP).andShipTimeLessThan(expired).andDeletedEqualTo(false);
        return aswxmallOrderMapper.selectByExample(example);
    }

    public AswxmallOrder findBySn(String orderSn) {
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return aswxmallOrderMapper.selectOneByExample(example);
    }

    public Map<Object, Object> orderInfo(Integer userId) {
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<AswxmallOrder> orders = aswxmallOrderMapper.selectByExampleSelective(example, AswxmallOrder.Column.orderStatus, AswxmallOrder.Column.comments);

        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (AswxmallOrder order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getComments();
            } else {
                // do nothing
            }
        }

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;

    }

    public List<AswxmallOrder> queryComment(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        AswxmallOrderExample example = new AswxmallOrderExample();
        example.or().andCommentsGreaterThan((short) 0).andConfirmTimeLessThan(expired).andDeletedEqualTo(false);
        return aswxmallOrderMapper.selectByExample(example);
    }
}
