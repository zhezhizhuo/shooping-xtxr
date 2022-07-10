package com.qgj.common.core.domain;

import com.qgj.common.core.domain.model.UserCard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("提交订单实体类")
public class OrderUserEntity {

    @ApiModelProperty(required = true,value = "商品集合")
    List<UserCard> goods;
    @ApiModelProperty(required = true,value = "所选地址Id")
    private Long addressId;

    @ApiModelProperty(required = true,value = "订单总金额")
    private BigDecimal totalAmount;

    private List<Long> carIds;

    public List<Long> getCarIds() {
        return carIds;
    }

    public void setCarIds(List<Long> carIds) {
        this.carIds = carIds;
    }

    @ApiModelProperty(required = true,value = "订单备注")
    private String note;

    public List<UserCard> getGoods() {
        return goods;
    }

    public void setGoods(List<UserCard> goods) {
        this.goods = goods;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
