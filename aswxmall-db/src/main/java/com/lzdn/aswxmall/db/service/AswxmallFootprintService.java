package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallFootprintMapper;
import com.lzdn.aswxmall.db.domain.AswxmallFootprint;
import com.lzdn.aswxmall.db.domain.AswxmallFootprintExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallFootprintService {
    @Resource
    private AswxmallFootprintMapper footprintMapper;

    public List<AswxmallFootprint> queryByAddTime(Integer userId, Integer page, Integer size) {
        AswxmallFootprintExample example = new AswxmallFootprintExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        example.setOrderByClause(AswxmallFootprint.Column.addTime.desc());
        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }

    public AswxmallFootprint findById(Integer id) {
        return footprintMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        footprintMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(AswxmallFootprint footprint) {
        footprint.setAddTime(LocalDateTime.now());
        footprint.setUpdateTime(LocalDateTime.now());
        footprintMapper.insertSelective(footprint);
    }

    public List<AswxmallFootprint> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order) {
        AswxmallFootprintExample example = new AswxmallFootprintExample();
        AswxmallFootprintExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.valueOf(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }
}
