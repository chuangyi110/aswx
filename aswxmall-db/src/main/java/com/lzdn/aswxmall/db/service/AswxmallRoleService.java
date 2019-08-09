package com.lzdn.aswxmall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallRoleMapper;
import com.lzdn.aswxmall.db.domain.AswxmallRole;
import com.lzdn.aswxmall.db.domain.AswxmallRoleExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AswxmallRoleService {
    @Resource
    private AswxmallRoleMapper roleMapper;


    public Set<String> queryByIds(Integer[] roleIds) {
        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }

        AswxmallRoleExample example = new AswxmallRoleExample();
        example.or().andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<AswxmallRole> roleList = roleMapper.selectByExample(example);

        for(AswxmallRole role : roleList){
            roles.add(role.getName());
        }

        return roles;

    }

    public List<AswxmallRole> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        AswxmallRoleExample example = new AswxmallRoleExample();
        AswxmallRoleExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return roleMapper.selectByExample(example);
    }

    public AswxmallRole findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public void add(AswxmallRole role) {
        role.setAddTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertSelective(role);
    }

    public void deleteById(Integer id) {
        roleMapper.logicalDeleteByPrimaryKey(id);
    }

    public void updateById(AswxmallRole role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public boolean checkExist(String name) {
        AswxmallRoleExample example = new AswxmallRoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return roleMapper.countByExample(example) != 0;
    }

    public List<AswxmallRole> queryAll() {
        AswxmallRoleExample example = new AswxmallRoleExample();
        example.or().andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }
}
