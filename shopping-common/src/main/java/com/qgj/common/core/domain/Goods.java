package com.qgj.common.core.domain;

import com.qgj.common.core.domain.entity.Product;
import com.qgj.common.core.domain.entity.ProductHotSort;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
@ApiModel("View 商品模型")
public class Goods implements Serializable {

        @ApiModelProperty(value = "商品id")
        private Long id;
        @ApiModelProperty(value = "商品id")
        private String name;
        @ApiModelProperty(value = "商品名称")
        private String desc;
        @ApiModelProperty(value = "商品描述")
        private String picture;
        @ApiModelProperty(value = "商品图片")
        private BigDecimal price;

        public Goods() {
        }


        public Goods(Product product) {
                this.id = product.getProductId();
                this.name = product.getProductName();
                this.desc = product.getDescript();
                this.picture = product.getPicture();
                this.price = product.getPrice();
                this.count = product.getCount();
        }

        private Long count;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDesc() {
                return desc;
        }

        public void setDesc(String desc) {
                this.desc = desc;
        }

        public String getPicture() {
                return picture;
        }

        public void setPicture(String picture) {
                this.picture = picture;
        }

        public BigDecimal getPrice() {
                return price;
        }

        public void setPrice(BigDecimal price) {
                this.price = price;
        }

        public Long getCount() {
                return count;
        }

        public void setCount(Long count) {
                this.count = count;
        }
}
