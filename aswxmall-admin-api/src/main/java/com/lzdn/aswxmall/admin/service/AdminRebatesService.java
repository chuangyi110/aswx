package com.lzdn.aswxmall.admin.service;


import com.lzdn.aswxmall.admin.vo.RebatesVo;
import com.lzdn.aswxmall.core.util.ResponseUtil;
import com.lzdn.aswxmall.db.domain.AswxmallRebates;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesCreateOrder;
import com.lzdn.aswxmall.db.domain.AswxmallRebatesWithdrawalOrder;
import com.lzdn.aswxmall.db.service.AswxmallRebatesCreateService;
import com.lzdn.aswxmall.db.service.AswxmallRebatesService;
import com.lzdn.aswxmall.db.service.AswxmallRebatesWithdrawalOrderService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AdminRebatesService {
    @Autowired
    private AswxmallRebatesService rebatesService;
    @Autowired
    private AswxmallRebatesCreateService aswxmallRebatesCreateService;
    @Autowired
    private AswxmallRebatesWithdrawalOrderService rebatesWithdrawalOrderService;

    public Object countRebatesSeletive(Integer uid, Integer page, Integer limit, String sort, String order) {
        List<AswxmallRebates> aswxmallRebatesList = rebatesService.selectRebatesByUid(uid);
        if(aswxmallRebatesList.size()>1){
            return ResponseUtil.serious();
        }
        if(aswxmallRebatesList.size()<=0){
            return ResponseUtil.unlist();
        }
        AswxmallRebates rebates = aswxmallRebatesList.get(0);
        List<AswxmallRebatesCreateOrder> rebatesCreateOrderList = aswxmallRebatesCreateService.selectOrderByUid(uid);
        List<AswxmallRebatesWithdrawalOrder> rebatesWithdrawalOrderList = rebatesWithdrawalOrderService.selectOrderByUid(uid);
        Map<Integer,RebatesVo.RebatesCount> map = new HashMap<>();
        for(AswxmallRebatesCreateOrder arco :rebatesCreateOrderList){
            RebatesVo.RebatesCount rvrc = new RebatesVo.RebatesCount();
            if(map.containsKey(arco.getOrderUserId())){
                rvrc = map.get(arco.getOrderUserId());
                rvrc.setOrderCount(rvrc.getOrderCount()+1);
                rvrc.setTotalRebates(rvrc.getTotalRebates().add(arco.getRebates()));
                rvrc.setTotalOrderActualPrice(rvrc.getTotalOrderActualPrice().add(arco.getOrderActualPrice()));
            }else {
                rvrc.setOrderUserId(arco.getOrderUserId());
                rvrc.setOrderCount(1);
                rvrc.setTotalRebates(arco.getRebates());
                rvrc.setTotalOrderActualPrice(arco.getOrderActualPrice());
            }
            map.put(arco.getOrderUserId(),rvrc);
        }
        Collection<RebatesVo.RebatesCount> values = map.values();
        List<RebatesVo.RebatesCount> rebatesCountList = new ArrayList <>(values);
        RebatesVo rebatesVo = new RebatesVo();
        rebatesVo.setAmount(rebates.getAmount());
        rebatesVo.setBalance(rebates.getBalance());
        rebatesVo.setWithdrawMoney(rebates.getWidthdraw());
        rebatesVo.setAswxmallRebatesWithdrawalOrderList(rebatesWithdrawalOrderList);
        rebatesVo.setRebatesCountList(rebatesCountList);
        return ResponseUtil.ok(rebatesVo);
    }
}
