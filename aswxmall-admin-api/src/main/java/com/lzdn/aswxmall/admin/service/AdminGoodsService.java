package com.lzdn.aswxmall.admin.service;

import com.lzdn.aswxmall.db.domain.*;
import com.lzdn.aswxmall.db.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lzdn.aswxmall.admin.dto.GoodsAllinone;
import com.lzdn.aswxmall.admin.vo.CatVo;
import com.lzdn.aswxmall.core.qcode.QCodeService;
import com.lzdn.aswxmall.core.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lzdn.aswxmall.admin.util.AdminResponseCode.GOODS_NAME_EXIST;

@Service
public class AdminGoodsService {
    private final Log logger = LogFactory.getLog(AdminGoodsService.class);

    @Autowired
    private AswxmallGoodsService goodsService;
    @Autowired
    private AswxmallGoodsSpecificationService specificationService;
    @Autowired
    private AswxmallGoodsAttributeService attributeService;
    @Autowired
    private AswxmallGoodsProductService productService;
    @Autowired
    private AswxmallCategoryService categoryService;
    @Autowired
    private AswxmallBrandService brandService;

    @Autowired
    private QCodeService qCodeService;

    public Object list(String goodsSn, String name,
                       Integer page, Integer limit, String sort, String order) {
        List<AswxmallGoods> goodsList = goodsService.querySelective(goodsSn, name, page, limit, sort, order);
        return ResponseUtil.okList(goodsList);
    }

    private Object validate(GoodsAllinone goodsAllinone) {
        AswxmallGoods goods = goodsAllinone.getGoods();
        String name = goods.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String goodsSn = goods.getGoodsSn();
        if (StringUtils.isEmpty(goodsSn)) {
            return ResponseUtil.badArgument();
        }
        // 品牌商可以不设置，如果设置则需要验证品牌商存在
        Integer brandId = goods.getBrandId();
        if (brandId != null && brandId != 0) {
            if (brandService.findById(brandId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        }
        // 分类可以不设置，如果设置则需要验证分类存在
        Integer categoryId = goods.getCategoryId();
        if (categoryId != null && categoryId != 0) {
            if (categoryService.findById(categoryId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        }

        AswxmallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        for (AswxmallGoodsAttribute attribute : attributes) {
            String attr = attribute.getAttribute();
            if (StringUtils.isEmpty(attr)) {
                return ResponseUtil.badArgument();
            }
            String value = attribute.getValue();
            if (StringUtils.isEmpty(value)) {
                return ResponseUtil.badArgument();
            }
        }

        AswxmallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        for (AswxmallGoodsSpecification specification : specifications) {
            String spec = specification.getSpecification();
            if (StringUtils.isEmpty(spec)) {
                return ResponseUtil.badArgument();
            }
            String value = specification.getValue();
            if (StringUtils.isEmpty(value)) {
                return ResponseUtil.badArgument();
            }
        }

        AswxmallGoodsProduct[] products = goodsAllinone.getProducts();
        for (AswxmallGoodsProduct product : products) {
            Integer number = product.getNumber();
            if (number == null || number < 0) {
                return ResponseUtil.badArgument();
            }

            BigDecimal price = product.getPrice();
            if (price == null) {
                return ResponseUtil.badArgument();
            }

            String[] productSpecifications = product.getSpecifications();
            if (productSpecifications.length == 0) {
                return ResponseUtil.badArgument();
            }
        }

        return null;
    }

    /**
     * 编辑商品
     * <p>
     * TODO
     * 目前商品修改的逻辑是
     * 1. 更新Aswxmall_goods表
     * 2. 逻辑删除Aswxmall_goods_specification、Aswxmall_goods_attribute、Aswxmall_goods_product
     * 3. 添加Aswxmall_goods_specification、Aswxmall_goods_attribute、Aswxmall_goods_product
     * <p>
     * 这里商品三个表的数据采用删除再添加的策略是因为
     * 商品编辑页面，支持管理员添加删除商品规格、添加删除商品属性，因此这里仅仅更新是不可能的，
     * 只能删除三个表旧的数据，然后添加新的数据。
     * 但是这里又会引入新的问题，就是存在订单商品货品ID指向了失效的商品货品表。
     * 因此这里会拒绝管理员编辑商品，如果订单或购物车中存在商品。
     * 所以这里可能需要重新设计。
     */
    @Transactional
    public Object update(GoodsAllinone goodsAllinone) {
        Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }

        AswxmallGoods goods = goodsAllinone.getGoods();
        AswxmallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        AswxmallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        AswxmallGoodsProduct[] products = goodsAllinone.getProducts();

        Integer id = goods.getId();

        //将生成的分享图片地址写入数据库
        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
        goods.setShareUrl(url);

        // 商品基本信息表Aswxmall_goods
        if (goodsService.updateById(goods) == 0) {
            throw new RuntimeException("更新数据失败");
        }

        Integer gid = goods.getId();
        specificationService.deleteByGid(gid);
        attributeService.deleteByGid(gid);
        productService.deleteByGid(gid);

        // 商品规格表Aswxmall_goods_specification
        for (AswxmallGoodsSpecification specification : specifications) {
            specification.setGoodsId(goods.getId());
            specificationService.add(specification);
        }

        // 商品参数表Aswxmall_goods_attribute
        for (AswxmallGoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            attributeService.add(attribute);
        }

        // 商品货品表Aswxmall_product
        for (AswxmallGoodsProduct product : products) {
            product.setGoodsId(goods.getId());
            productService.add(product);
        }

        return ResponseUtil.ok();
    }

    @Transactional
    public Object delete(AswxmallGoods goods) {
        Integer id = goods.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        Integer gid = goods.getId();
        goodsService.deleteById(gid);
        specificationService.deleteByGid(gid);
        attributeService.deleteByGid(gid);
        productService.deleteByGid(gid);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object create(GoodsAllinone goodsAllinone) {
        Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }

        AswxmallGoods goods = goodsAllinone.getGoods();
        System.out.println(goods);
        AswxmallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        AswxmallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        AswxmallGoodsProduct[] products = goodsAllinone.getProducts();

        String name = goods.getName();
        if (goodsService.checkExistByName(name)) {
            return ResponseUtil.fail(GOODS_NAME_EXIST, "商品名已经存在");
        }

        // 商品基本信息表Aswxmall_goods
        goodsService.add(goods);

        //将生成的分享图片地址写入数据库
        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
        if (!StringUtils.isEmpty(url)) {
            goods.setShareUrl(url);
            if (goodsService.updateById(goods) == 0) {
                throw new RuntimeException("更新数据失败");
            }
        }

        // 商品规格表Aswxmall_goods_specification
        for (AswxmallGoodsSpecification specification : specifications) {
            specification.setGoodsId(goods.getId());
            specificationService.add(specification);
        }

        // 商品参数表Aswxmall_goods_attribute
        for (AswxmallGoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            attributeService.add(attribute);
        }

        // 商品货品表Aswxmall_product
        for (AswxmallGoodsProduct product : products) {
            product.setGoodsId(goods.getId());
            productService.add(product);
        }
        return ResponseUtil.ok();
    }

    public Object list2() {
        // http://element-cn.eleme.io/#/zh-CN/component/cascader
        // 管理员设置“所属分类”
        List<AswxmallCategory> l1CatList = categoryService.queryL1();
        List<CatVo> categoryList = new ArrayList<>(l1CatList.size());

        for (AswxmallCategory l1 : l1CatList) {
            CatVo l1CatVo = new CatVo();
            l1CatVo.setValue(l1.getId());
            l1CatVo.setLabel(l1.getName());

            List<AswxmallCategory> l2CatList = categoryService.queryByPid(l1.getId());
            List<CatVo> children = new ArrayList<>(l2CatList.size());
            for (AswxmallCategory l2 : l2CatList) {
                CatVo l2CatVo = new CatVo();
                l2CatVo.setValue(l2.getId());
                l2CatVo.setLabel(l2.getName());
                children.add(l2CatVo);
            }
            l1CatVo.setChildren(children);

            categoryList.add(l1CatVo);
        }

        // http://element-cn.eleme.io/#/zh-CN/component/select
        // 管理员设置“所属品牌商”
        List<AswxmallBrand> list = brandService.all();
        List<Map<String, Object>> brandList = new ArrayList<>(l1CatList.size());
        for (AswxmallBrand brand : list) {
            Map<String, Object> b = new HashMap<>(2);
            b.put("value", brand.getId());
            b.put("label", brand.getName());
            brandList.add(b);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", categoryList);
        data.put("brandList", brandList);
        return ResponseUtil.ok(data);
    }

    public Object detail(Integer id) {
        AswxmallGoods goods = goodsService.findById(id);
        List<AswxmallGoodsProduct> products = productService.queryByGid(id);
        List<AswxmallGoodsSpecification> specifications = specificationService.queryByGid(id);
        List<AswxmallGoodsAttribute> attributes = attributeService.queryByGid(id);

        Integer categoryId = goods.getCategoryId();
        AswxmallCategory category = categoryService.findById(categoryId);
        Integer[] categoryIds = new Integer[]{};
        if (category != null) {
            Integer parentCategoryId = category.getPid();
            categoryIds = new Integer[]{parentCategoryId, categoryId};
        }

        Map<String, Object> data = new HashMap<>();
        data.put("goods", goods);
        data.put("specifications", specifications);
        data.put("products", products);
        data.put("attributes", attributes);
        data.put("categoryIds", categoryIds);

        return ResponseUtil.ok(data);
    }

}
