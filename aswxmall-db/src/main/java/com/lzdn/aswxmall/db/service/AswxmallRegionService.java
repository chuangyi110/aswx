package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallRegionMapper;
import com.lzdn.aswxmall.db.domain.AswxmallRegion;
import com.lzdn.aswxmall.db.domain.AswxmallRegionExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AswxmallRegionService {

    @Resource
    private AswxmallRegionMapper regionMapper;

    public List<AswxmallRegion> getAll(){
        AswxmallRegionExample example = new AswxmallRegionExample();
        byte b = 4;
        example.or().andTypeNotEqualTo(b);
        return regionMapper.selectByExample(example);
    }

    public List<AswxmallRegion> queryByPid(Integer parentId) {
        AswxmallRegionExample example = new AswxmallRegionExample();
        example.or().andPidEqualTo(parentId);
        return regionMapper.selectByExample(example);
    }

    public AswxmallRegion findById(Integer id) {
        return regionMapper.selectByPrimaryKey(id);
    }

    public List<AswxmallRegion> querySelective(String name, Integer code, Integer page, Integer size, String sort, String order) {
        AswxmallRegionExample example = new AswxmallRegionExample();
        AswxmallRegionExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(code)) {
            criteria.andCodeEqualTo(code);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return regionMapper.selectByExample(example);
    }

}
