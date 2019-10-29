package com.lzdn.aswxmall.admin.dto;

import com.lzdn.aswxmall.db.domain.AswxmallGoods;
import com.lzdn.aswxmall.db.domain.AswxmallGoodsAttribute;
import com.lzdn.aswxmall.db.domain.AswxmallGoodsProduct;
import com.lzdn.aswxmall.db.domain.AswxmallGoodsSpecification;

import java.util.Arrays;

public class GoodsAllinone {
    AswxmallGoods goods;
    AswxmallGoodsSpecification[] specifications;
    AswxmallGoodsAttribute[] attributes;
    AswxmallGoodsProduct[] products;

    public AswxmallGoods getGoods() {
        return goods;
    }

    public void setGoods(AswxmallGoods goods) {
        this.goods = goods;
    }

    public AswxmallGoodsProduct[] getProducts() {
        return products;
    }

    public void setProducts(AswxmallGoodsProduct[] products) {
        this.products = products;
    }

    public AswxmallGoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(AswxmallGoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public AswxmallGoodsAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AswxmallGoodsAttribute[] attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "GoodsAllinone{" +
                "goods=" + goods +
                ", specifications=" + Arrays.toString(specifications) +
                ", attributes=" + Arrays.toString(attributes) +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}
