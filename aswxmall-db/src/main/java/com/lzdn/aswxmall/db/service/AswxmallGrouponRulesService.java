package com.lzdn.aswxmall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallGoodsMapper;
import com.lzdn.aswxmall.db.dao.AswxmallGrouponRulesMapper;
import com.lzdn.aswxmall.db.domain.AswxmallGoods;
import com.lzdn.aswxmall.db.domain.AswxmallGrouponRules;
import com.lzdn.aswxmall.db.domain.AswxmallGrouponRulesExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallGrouponRulesService {
    @Resource
    private AswxmallGrouponRulesMapper mapper;
    @Resource
    private AswxmallGoodsMapper goodsMapper;
    private AswxmallGoods.Column[] goodsColumns = new AswxmallGoods.Column[]{AswxmallGoods.Column.id, AswxmallGoods.Column.name, AswxmallGoods.Column.brief, AswxmallGoods.Column.picUrl, AswxmallGoods.Column.counterPrice, AswxmallGoods.Column.retailPrice};

    public int createRules(AswxmallGrouponRules rules) {
        rules.setAddTime(LocalDateTime.now());
        rules.setUpdateTime(LocalDateTime.now());
        return mapper.insertSelective(rules);
    }

    /**
     * 根据ID查找对应团购项
     *
     * @param id
     * @return
     */
    public AswxmallGrouponRules queryById(Integer id) {
        AswxmallGrouponRulesExample example = new AswxmallGrouponRulesExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return mapper.selectOneByExample(example);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    public List<AswxmallGrouponRules> queryByGoodsId(Integer goodsId) {
        AswxmallGrouponRulesExample example = new AswxmallGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }

    /**
     * 获取首页团购活动列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<AswxmallGrouponRules> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    public List<AswxmallGrouponRules> queryList(Integer page, Integer limit, String sort, String order) {
        AswxmallGrouponRulesExample example = new AswxmallGrouponRulesExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        return mapper.selectByExample(example);
    }

    /**
     * 判断某个团购活动是否已经过期
     *
     * @return
     */
    public boolean isExpired(AswxmallGrouponRules rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    /**
     * 获取团购活动列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<AswxmallGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        AswxmallGrouponRulesExample example = new AswxmallGrouponRulesExample();
        example.setOrderByClause(sort + " " + order);

        AswxmallGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);
    }

    public void delete(Integer id) {
        mapper.logicalDeleteByPrimaryKey(id);
    }

    public int updateById(AswxmallGrouponRules grouponRules) {
        grouponRules.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(grouponRules);
    }
}