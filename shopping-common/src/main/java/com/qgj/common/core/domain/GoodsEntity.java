package com.qgj.common.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class GoodsEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("spuCode")
    private String spuCode;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("oldPrice")
    private String oldPrice;
    @JsonProperty("discount")
    private Double discount;
    @JsonProperty("inventory")
    private Integer inventory = 10;

    public List<CategorysEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorysEntity> categories) {
        this.categories = categories;
    }

    //24小时或热度
    private List<Goods> hotByDay;
    //销售多少个
    @JsonProperty("salesCount")
        private Integer salesCount;
    //评论量
    @JsonProperty("commentCount")
    private Integer commentCount;
    //收藏人数
    @JsonProperty("collectCount")
    private Integer collectCount;

    private List<String>mainPictures;

    @JsonProperty("praisePercent")
    private String praisePercent;

    private List<CategorysEntity>  categories;

    public List<String> getMainPictures() {
        return mainPictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpuCode() {
        return spuCode;
    }

    public void setSpuCode(String spuCode) {
        this.spuCode = spuCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public List<Goods> getHotByDay() {
        return hotByDay;
    }

    public void setHotByDay(List<Goods> hotByDay) {
        this.hotByDay = hotByDay;
    }

    public Integer getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public void setMainPictures(List<String> mainPictures) {
        this.mainPictures = mainPictures;
    }

    public String getPraisePraise() {
        if (this.praisePercent ==null){
            this.praisePercent = "0.00";
        }
        return this.praisePercent;
    }

    public void setPraisePraise(String praisePraise) {
        this.praisePercent = praisePraise;
    }
}
