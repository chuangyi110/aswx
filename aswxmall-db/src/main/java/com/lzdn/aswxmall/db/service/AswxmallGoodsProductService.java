package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallGoodsProductMapper;
import com.lzdn.aswxmall.db.dao.GoodsProductMapper;
import com.lzdn.aswxmall.db.domain.AswxmallGoodsProduct;
import com.lzdn.aswxmall.db.domain.AswxmallGoodsProductExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AswxmallGoodsProductService {
    @Resource
    private AswxmallGoodsProductMapper aswxmallGoodsProductMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;

    public List<AswxmallGoodsProduct> queryByGid(Integer gid) {
        AswxmallGoodsProductExample example = new AswxmallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return aswxmallGoodsProductMapper.selectByExample(example);
    }

    public AswxmallGoodsProduct findById(Integer id) {
        return aswxmallGoodsProductMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        aswxmallGoodsProductMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(AswxmallGoodsProduct goodsProduct) {
        goodsProduct.setAddTime(LocalDateTime.now());
        goodsProduct.setUpdateTime(LocalDateTime.now());
        aswxmallGoodsProductMapper.insertSelective(goodsProduct);
    }

    public int count() {
        AswxmallGoodsProductExample example = new AswxmallGoodsProductExample();
        example.or().andDeletedEqualTo(false);
        return (int) aswxmallGoodsProductMapper.countByExample(example);
    }

    public void deleteByGid(Integer gid) {
        AswxmallGoodsProductExample example = new AswxmallGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid);
        aswxmallGoodsProductMapper.logicalDeleteByExample(example);
    }

    public int addStock(Integer id, Short num){
        return goodsProductMapper.addStock(id, num);
    }

    public int reduceStock(Integer id, Short num){
        return goodsProductMapper.reduceStock(id, num);
    }
}