package com.lzdn.aswxmall.db.service;

import com.github.pagehelper.PageHelper;
import com.lzdn.aswxmall.db.dao.AswxmallAddressMapper;
import com.lzdn.aswxmall.db.domain.AswxmallAddress;
import com.lzdn.aswxmall.db.domain.AswxmallAddressExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallAddressService {
    @Resource
    private AswxmallAddressMapper addressMapper;

    public List<AswxmallAddress> queryByUid(Integer uid) {
        AswxmallAddressExample example = new AswxmallAddressExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        return addressMapper.selectByExample(example);
    }

    public AswxmallAddress query(Integer userId, Integer id) {
        AswxmallAddressExample example = new AswxmallAddressExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return addressMapper.selectOneByExample(example);
    }

    public int add(AswxmallAddress address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.insertSelective(address);
    }

    public int update(AswxmallAddress address) {
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    public void delete(Integer id) {
        addressMapper.logicalDeleteByPrimaryKey(id);
    }

    public AswxmallAddress findDefault(Integer userId) {
        AswxmallAddressExample example = new AswxmallAddressExample();
        example.or().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return addressMapper.selectOneByExample(example);
    }

    public void resetDefault(Integer userId) {
        AswxmallAddress address = new AswxmallAddress();
        address.setIsDefault(false);
        address.setUpdateTime(LocalDateTime.now());
        AswxmallAddressExample example = new AswxmallAddressExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        addressMapper.updateByExampleSelective(address, example);
    }

    public List<AswxmallAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        AswxmallAddressExample example = new AswxmallAddressExample();
        AswxmallAddressExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return addressMapper.selectByExample(example);
    }
}
