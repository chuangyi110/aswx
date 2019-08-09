package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallGoodsAttributeMapper;
import com.lzdn.aswxmall.db.domain.AswxmallGoodsAttribute;
import com.lzdn.aswxmall.db.domain.AswxmallGoodsAttributeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallGoodsAttributeService {
    @Resource
    private AswxmallGoodsAttributeMapper goodsAttributeMapper;

    public List<AswxmallGoodsAttribute> queryByGid(Integer goodsId) {
        AswxmallGoodsAttributeExample example = new AswxmallGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return goodsAttributeMapper.selectByExample(example);
    }

    public void add(AswxmallGoodsAttribute goodsAttribute) {
        goodsAttribute.setAddTime(LocalDateTime.now());
        goodsAttribute.setUpdateTime(LocalDateTime.now());
        goodsAttributeMapper.insertSelective(goodsAttribute);
    }

    public AswxmallGoodsAttribute findById(Integer id) {
        return goodsAttributeMapper.selectByPrimaryKey(id);
    }

    public void deleteByGid(Integer gid) {
        AswxmallGoodsAttributeExample example = new AswxmallGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(gid);
        goodsAttributeMapper.logicalDeleteByExample(example);
    }
}
