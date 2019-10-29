package com.lzdn.aswxmall.admin.dto;

import com.lzdn.aswxmall.core.util.excel.ExcelColumn;
import com.lzdn.aswxmall.db.domain.AswxmallGoods;

import java.math.BigDecimal;

public class GoodsExcel {

    @ExcelColumn(value = "商品编号", col = 1)
    private String goodsSn;

    @ExcelColumn(value = "名称", col = 2)
    private String name;

    @ExcelColumn(value = "类目ID", col = 3)
    private Integer categoryId;

    @ExcelColumn(value = "品牌商ID", col = 4)
    private Integer brandId;

    @ExcelColumn(value = "专柜价格", col = 5)
    private BigDecimal counterPrice;

    @ExcelColumn(value = "当前价格", col = 6)
    private BigDecimal retailPrice;

    @ExcelColumn(value = "VIP价格", col = 7)
    private BigDecimal retailVPrice;

    @ExcelColumn(value = "VVIP价格", col = 8)
    private BigDecimal retailVVPrice;

    @ExcelColumn(value = "是否新品", col = 9)
    private Boolean isNew;

    @ExcelColumn(value = "是否热品", col = 10)
    private Boolean isHot;

    @ExcelColumn(value = "是否在售", col = 11)
    private Boolean isOnSale;

    @ExcelColumn(value = "商品介绍", col = 12)
    private String brief;

    @ExcelColumn(value = "商品单位", col = 13)
    private String unit;

    @ExcelColumn(value = "邮费", col = 14)
    private BigDecimal freightPrice;

    @ExcelColumn(value = "商品规格", col = 15)
    private String specification;

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getRetailVPrice() {
        return retailVPrice;
    }

    public void setRetailVPrice(BigDecimal retailVPrice) {
        this.retailVPrice = retailVPrice;
    }

    public BigDecimal getRetailVVPrice() {
        return retailVVPrice;
    }

    public void setRetailVVPrice(BigDecimal retailVVPrice) {
        this.retailVVPrice = retailVVPrice;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public AswxmallGoods toDTO(AswxmallGoods goods, GoodsExcel d){
        goods.setName(d.getName());
        goods.setGoodsSn(d.getGoodsSn());
        goods.setCategoryId(d.getCategoryId());
        goods.setBrandId(d.getBrandId());
        goods.setBrief(d.getBrief());
        goods.setCounterPrice(d.getCounterPrice());
        goods.setFreightPrice(d.getFreightPrice());
        goods.setIsHot(d.getHot());
        goods.setIsNew(d.getNew());
        goods.setIsOnSale(d.getOnSale());
        goods.setRetailPrice(d.getRetailPrice());
        goods.setRetailVPrice(d.getRetailVPrice());
        goods.setRetailVVPrice(d.getRetailVVPrice());
        goods.setUnit(d.getUnit()!=null?d.getUnit():"件");
        goods.setDeleted(false);
        return goods;
    }

    @Override
    public String toString() {
        return "DO{" +
                "goodsSn='" + goodsSn + '\'' +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", counterPrice=" + counterPrice +
                ", retailPrice=" + retailPrice +
                ", retailVPrice=" + retailVPrice +
                ", retailVVPrice=" + retailVVPrice +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", isOnSale=" + isOnSale +
                ", brief='" + brief + '\'' +
                ", unit='" + unit + '\'' +
                ", freightPrice=" + freightPrice +
                ", specification='" + specification + '\'' +
                '}';
    }
    //    /**
//     * 宣传图片列表
//     */
//    private String[] gallery;
//
//    /**
//     * 关键字
//     */
//    private String keywords;
//    /**
//     * 排序
//     */
//    private Short sortOrder;
//
//    /**
//     * 商品图片
//     */
//    private String picUrl;
//
//    /**
//     * 分享图片
//     */
//    private String shareUrl;
//
//    /**
//     * 添加时间
//     */
//    private LocalDateTime addTime;
//
//    /**
//     * 更新时间
//     */
//    private LocalDateTime updateTime;
//
//    /**
//     * 逻辑删除
//     */
//    private Boolean deleted;
//
//    /**
//     * 商品详情
//     */
//    private String detail;
}

