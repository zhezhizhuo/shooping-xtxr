package com.qgj.common.core.domain.entity;

import com.qgj.common.annotation.Log;

import java.io.Serializable;

/**
 *
 */
public class ProductHotSort  implements Serializable {

        //  商品id

    private Long productId;
        //商品图片
    private String picture;
        //商品名字
    private String productName;
        //商品描述

    private String descript;
        //商品收藏次数

    private Long collectsCount;

        //商品浏览次数
        private Long footCount;

        //商品评论次数
    private Long commentCount;

        //商品总热度
      private Long hot;

    public ProductHotSort() {
    }

    @Override
    public String toString() {
        return "ProductHotSort{" +
                "productId=" + productId +
                ", picture='" + picture + '\'' +
                ", productName='" + productName + '\'' +
                ", descript='" + descript + '\'' +
                ", collectsCount=" + collectsCount +
                ", footCount=" + footCount +
                ", commentCount=" + commentCount +
                ", hot=" + hot +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Long getCollectsCount() {
        return collectsCount;
    }

    public void setCollectsCount(Long collectsCount) {
        this.collectsCount = collectsCount;
    }

    public Long getFootCount() {
        return footCount;
    }

    public void setFootCount(Long footCount) {
        this.footCount = footCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getHot() {
        return hot;
    }

    public void setHot(Long hot) {
        this.hot = hot;
    }
}
