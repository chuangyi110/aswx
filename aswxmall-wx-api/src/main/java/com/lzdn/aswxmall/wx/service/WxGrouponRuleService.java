package com.lzdn.aswxmall.wx.service;

import com.github.pagehelper.Page;
import com.lzdn.aswxmall.db.domain.AswxmallGoods;
import com.lzdn.aswxmall.db.domain.AswxmallGrouponRules;
import com.lzdn.aswxmall.db.service.AswxmallGoodsService;
import com.lzdn.aswxmall.db.service.AswxmallGrouponRulesService;
import com.lzdn.aswxmall.db.service.AswxmallGrouponService;
import com.lzdn.aswxmall.wx.vo.GrouponRuleVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGrouponRuleService {
    private final Log logger = LogFactory.getLog(WxGrouponRuleService.class);

    @Autowired
    private AswxmallGrouponRulesService grouponRulesService;
    @Autowired
    private AswxmallGrouponService grouponService;
    @Autowired
    private AswxmallGoodsService goodsService;


    public List<GrouponRuleVo> queryList(Integer page, Integer size) {
        return queryList(page, size, "add_time", "desc");
    }


    public List<GrouponRuleVo> queryList(Integer page, Integer size, String sort, String order) {
        Page<AswxmallGrouponRules> grouponRulesList = (Page)grouponRulesService.queryList(page, size, sort, order);

        Page<GrouponRuleVo> grouponList = new Page<GrouponRuleVo>();
        grouponList.setPages(grouponRulesList.getPages());
        grouponList.setPageNum(grouponRulesList.getPageNum());
        grouponList.setPageSize(grouponRulesList.getPageSize());
        grouponList.setTotal(grouponRulesList.getTotal());

        for (AswxmallGrouponRules rule : grouponRulesList) {
            Integer goodsId = rule.getGoodsId();
            AswxmallGoods goods = goodsService.findById(goodsId);
            if (goods == null) {
                continue;
            }

            GrouponRuleVo grouponRuleVo = new GrouponRuleVo();
            grouponRuleVo.setId(goods.getId());
            grouponRuleVo.setName(goods.getName());
            grouponRuleVo.setBrief(goods.getBrief());
            grouponRuleVo.setPicUrl(goods.getPicUrl());
            grouponRuleVo.setCounterPrice(goods.getCounterPrice());
            grouponRuleVo.setRetailPrice(goods.getRetailPrice());
            grouponRuleVo.setGrouponPrice(goods.getRetailPrice().subtract(rule.getDiscount()));
            grouponRuleVo.setGrouponDiscount(rule.getDiscount());
            grouponRuleVo.setGrouponMember(rule.getDiscountMember());
            grouponList.add(grouponRuleVo);
        }

        return grouponList;
    }
}