package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallPermissionMapper;
import com.lzdn.aswxmall.db.domain.AswxmallPermission;
import com.lzdn.aswxmall.db.domain.AswxmallPermissionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AswxmallPermissionService {
    @Resource
    private AswxmallPermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        AswxmallPermissionExample example = new AswxmallPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<AswxmallPermission> permissionList = permissionMapper.selectByExample(example);

        for(AswxmallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        AswxmallPermissionExample example = new AswxmallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<AswxmallPermission> permissionList = permissionMapper.selectByExample(example);

        for(AswxmallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        AswxmallPermissionExample example = new AswxmallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        AswxmallPermissionExample example = new AswxmallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(AswxmallPermission AswxmallPermission) {
        AswxmallPermission.setAddTime(LocalDateTime.now());
        AswxmallPermission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(AswxmallPermission);
    }
}
