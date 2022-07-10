package com.qgj.common.core.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.qgj.common.annotation.Excel;
import com.qgj.common.core.domain.model.UserCard;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserOrderEntity implements Serializable {


    private String  id;


    /**
     * 订单总金额
     */
    @Excel(name = "订单总金额")
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;


    @ApiModelProperty(required = true,value = "商品集合")
    List<Goods> goods;


    /**
     * 应付金额（实际支付金额）
     */
    @Excel(name = "应付金额", readConverterExp = "实=际支付金额")
    @ApiModelProperty("应付金额")
    private BigDecimal payAmount;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("添加时间")
    private Date createTime;



    /**
     * 确认收货状态：0-&gt;未确认；1-&gt;已确认
     */
    @Excel(name = "确认收货状态：0-&gt;未确认；1-&gt;已确认")
    @ApiModelProperty("确认收货状态：0-&gt;未确认；1-&gt;已确认")
    private Integer confirmStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    private  Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }



    /**
     * 收货人姓名
     */
    @Excel(name = "收货人姓名")
    @ApiModelProperty("收货人姓名")
    private String receiverName;

    /**
     * 收货人电话
     */
    @Excel(name = "收货人电话")
    @ApiModelProperty("收货人电话")
    private String receiverPhone;

    /**
     * 详细地址
     */
    @Excel(name = "收货人地址")
    @ApiModelProperty("收货人地址")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
