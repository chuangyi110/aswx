package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallUserMapper;
import com.lzdn.aswxmall.db.domain.AswxmallUser;
import com.lzdn.aswxmall.db.domain.AswxmallUserExample;
import com.lzdn.aswxmall.db.domain.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallUserService {
    @Resource
    private AswxmallUserMapper userMapper;

    public AswxmallUser findById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public UserVo findUserVoById(Integer userId) {
        AswxmallUser user = findById(userId);
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    public AswxmallUser queryByOid(String openId) {
        AswxmallUserExample example = new AswxmallUserExample();
        example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
        return userMapper.selectOneByExample(example);
    }

    public void add(AswxmallUser user) {
        user.setAddTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertSelective(user);
    }

    public int updateById(AswxmallUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public List<AswxmallUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order) {
        AswxmallUserExample example = new AswxmallUserExample();
        AswxmallUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return userMapper.selectByExample(example);
    }

    public int count() {
        AswxmallUserExample example = new AswxmallUserExample();
        example.or().andDeletedEqualTo(false);

        return (int) userMapper.countByExample(example);
    }

    public List<AswxmallUser> queryByUsername(String username) {
        AswxmallUserExample example = new AswxmallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public boolean checkByUsername(String username) {
        AswxmallUserExample example = new AswxmallUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return userMapper.countByExample(example) != 0;
    }

    public List<AswxmallUser> queryByMobile(String mobile) {
        AswxmallUserExample example = new AswxmallUserExample();
        example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public List<AswxmallUser> queryByOpenid(String openid) {
        AswxmallUserExample example = new AswxmallUserExample();
        example.or().andWeixinOpenidEqualTo(openid).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        userMapper.logicalDeleteByPrimaryKey(id);
    }
}
