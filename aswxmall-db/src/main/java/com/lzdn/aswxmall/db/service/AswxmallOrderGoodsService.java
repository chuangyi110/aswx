package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallOrderGoodsMapper;
import com.lzdn.aswxmall.db.domain.AswxmallOrderGoods;
import com.lzdn.aswxmall.db.domain.AswxmallOrderGoodsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallOrderGoodsService {
    @Resource
    private AswxmallOrderGoodsMapper orderGoodsMapper;

    public int add(AswxmallOrderGoods orderGoods) {
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        return orderGoodsMapper.insertSelective(orderGoods);
    }

    public List<AswxmallOrderGoods> queryByOid(Integer orderId) {
        AswxmallOrderGoodsExample example = new AswxmallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public List<AswxmallOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId) {
        AswxmallOrderGoodsExample example = new AswxmallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public AswxmallOrderGoods findById(Integer id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }

    public void updateById(AswxmallOrderGoods orderGoods) {
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    public Short getComments(Integer orderId) {
        AswxmallOrderGoodsExample example = new AswxmallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        long count = orderGoodsMapper.countByExample(example);
        return (short) count;
    }

    public boolean checkExist(Integer goodsId) {
        AswxmallOrderGoodsExample example = new AswxmallOrderGoodsExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.countByExample(example) != 0;
    }
}
