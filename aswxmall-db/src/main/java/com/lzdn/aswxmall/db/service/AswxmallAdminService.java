package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallAdminMapper;
import com.lzdn.aswxmall.db.domain.AswxmallAdmin;
import com.lzdn.aswxmall.db.domain.AswxmallAdminExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallAdminService {
    private final AswxmallAdmin.Column[] result = new AswxmallAdmin.Column[]{AswxmallAdmin.Column.id, AswxmallAdmin.Column.username, AswxmallAdmin.Column.avatar, AswxmallAdmin.Column.roleIds};
    @Resource
    private AswxmallAdminMapper adminMapper;

    public List<AswxmallAdmin> findAdmin(String username) {
        AswxmallAdminExample example = new AswxmallAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public AswxmallAdmin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<AswxmallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        AswxmallAdminExample example = new AswxmallAdminExample();
        AswxmallAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }

    public int updateById(AswxmallAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(AswxmallAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    public AswxmallAdmin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    public List<AswxmallAdmin> all() {
        AswxmallAdminExample example = new AswxmallAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
