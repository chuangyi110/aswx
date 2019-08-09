package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallStorageMapper;
import com.lzdn.aswxmall.db.domain.AswxmallStorage;
import com.lzdn.aswxmall.db.domain.AswxmallStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallStorageService {
    @Autowired
    private AswxmallStorageMapper storageMapper;

    public void deleteByKey(String key) {
        AswxmallStorageExample example = new AswxmallStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    public void add(AswxmallStorage storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    public AswxmallStorage findByKey(String key) {
        AswxmallStorageExample example = new AswxmallStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);
    }

    public int update(AswxmallStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public AswxmallStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<AswxmallStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        AswxmallStorageExample example = new AswxmallStorageExample();
        AswxmallStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }
}
