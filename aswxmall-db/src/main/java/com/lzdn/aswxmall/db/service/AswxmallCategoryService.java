package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallCategoryMapper;
import com.lzdn.aswxmall.db.domain.AswxmallCategory;
import com.lzdn.aswxmall.db.domain.AswxmallCategoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallCategoryService {
    @Resource
    private AswxmallCategoryMapper categoryMapper;
    private AswxmallCategory.Column[] CHANNEL = {AswxmallCategory.Column.id, AswxmallCategory.Column.name, AswxmallCategory.Column.iconUrl};

    public List<AswxmallCategory> queryL1WithoutRecommend(int offset, int limit) {
        AswxmallCategoryExample example = new AswxmallCategoryExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);
    }

    public List<AswxmallCategory> queryL1(int offset, int limit) {
        AswxmallCategoryExample example = new AswxmallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);
    }

    public List<AswxmallCategory> queryL1() {
        AswxmallCategoryExample example = new AswxmallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<AswxmallCategory> queryByPid(Integer pid) {
        AswxmallCategoryExample example = new AswxmallCategoryExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<AswxmallCategory> queryL2ByIds(List<Integer> ids) {
        AswxmallCategoryExample example = new AswxmallCategoryExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public AswxmallCategory findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public List<AswxmallCategory> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        AswxmallCategoryExample example = new AswxmallCategoryExample();
        AswxmallCategoryExample.Criteria criteria = example.createCriteria();

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
        return categoryMapper.selectByExample(example);
    }

    public int updateById(AswxmallCategory category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    public void deleteById(Integer id) {
        categoryMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(AswxmallCategory category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertSelective(category);
    }

    public List<AswxmallCategory> queryChannel() {
        AswxmallCategoryExample example = new AswxmallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExampleSelective(example, CHANNEL);
    }
}
