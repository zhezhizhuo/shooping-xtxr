package com.qgj.common.core.domain;

import com.qgj.common.core.domain.entity.Product;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods_Detail implements Serializable {
    public Integer getGoods_id() {
        return goods_id;
    }

    public Goods_Detail() {
    }

    public Goods_Detail(Product product) {
        this.goods_id = Math.toIntExact(product.getProductId());
        this.goods_name = product.getProductName();

        this.price = product.getPrice();
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private  Integer goods_id;

    private String goods_name;

    private Integer quantity;

    private BigDecimal price;

}
