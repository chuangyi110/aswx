package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallBrandMapper;
import com.lzdn.aswxmall.db.domain.AswxmallBrand;
import com.lzdn.aswxmall.db.domain.AswxmallBrandExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallBrandService {
    @Resource
    private AswxmallBrandMapper brandMapper;
    private AswxmallBrand.Column[] columns = new AswxmallBrand.Column[]{AswxmallBrand.Column.id, AswxmallBrand.Column.name, AswxmallBrand.Column.desc, AswxmallBrand.Column.picUrl, AswxmallBrand.Column.floorPrice};

    public List<AswxmallBrand> query(Integer page, Integer limit, String sort, String order) {
        AswxmallBrandExample example = new AswxmallBrandExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return brandMapper.selectByExampleSelective(example, columns);
    }

    public List<AswxmallBrand> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public AswxmallBrand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    public List<AswxmallBrand> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        AswxmallBrandExample example = new AswxmallBrandExample();
        AswxmallBrandExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return brandMapper.selectByExample(example);
    }

    public int updateById(AswxmallBrand brand) {
        brand.setUpdateTime(LocalDateTime.now());
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    public void deleteById(Integer id) {
        brandMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(AswxmallBrand brand) {
        brand.setAddTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        brandMapper.insertSelective(brand);
    }

    public List<AswxmallBrand> all() {
        AswxmallBrandExample example = new AswxmallBrandExample();
        example.or().andDeletedEqualTo(false);
        return brandMapper.selectByExample(example);
    }
}
