package com.qgj.common.core.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.qgj.common.annotation.Excel;
import com.qgj.common.core.domain.BaseEntity;

/**
 * 用户订单表对象 master_order
 *
 * @author qgj
 * @date 2022-04-25
 */
@ApiModel("用户订单表")
public class MasterOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long id;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    @ApiModelProperty("订单编号")
    private String orderSn;

    /**
     * 用户帐号
     */
    @Excel(name = "用户帐号")
    @ApiModelProperty("用户帐号")
    private String memberUsername;

    /**
     * 订单总金额
     */
    @Excel(name = "订单总金额")
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    /**
     * 应付金额（实际支付金额）
     */
    @Excel(name = "应付金额", readConverterExp = "实=际支付金额")
    @ApiModelProperty("应付金额")
    private BigDecimal payAmount;

    /**
     * 支付方式：0-&gt;未支付；1-&gt;支付宝；2-&gt;微信
     */
    @Excel(name = "支付方式：0-&gt;未支付；1-&gt;支付宝；2-&gt;微信")
    @ApiModelProperty("支付方式：0-&gt;未支付；1-&gt;支付宝；2-&gt;微信")
    private Integer payType;

    /**
     * 订单状态：0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;已取消
     */
    @Excel(name = "订单状态：0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;已取消")
    @ApiModelProperty("订单状态：0-&gt;待付款；1-&gt;待发货；2-&gt;已发货；3-&gt;已完成；4-&gt;已关闭；5-&gt;已取消")
    private Integer status;

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
    @Excel(name = "详细地址")
    @ApiModelProperty("详细地址")
    private Long addresssId;

    /**
     * 订单备注
     */
    @Excel(name = "订单备注")
    @ApiModelProperty("订单备注")
    private String note;

    /**
     * 确认收货状态：0-&gt;未确认；1-&gt;已确认
     */
    @Excel(name = "确认收货状态：0-&gt;未确认；1-&gt;已确认")
    @ApiModelProperty("确认收货状态：0-&gt;未确认；1-&gt;已确认")
    private Integer confirmStatus;

    /**
     * 订单删除状态：0-&gt;未删除；1-&gt;已删除
     */
    @Excel(name = "订单删除状态：0-&gt;未删除；1-&gt;已删除")
    @ApiModelProperty("订单删除状态：0-&gt;未删除；1-&gt;已删除")
    private Integer deleteStatus;

    /**
     * 下单时使用的积分
     */
    @Excel(name = "下单时使用的积分")
    @ApiModelProperty("下单时使用的积分")
    private Long useIntegration;

    /**
     * 支付时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("支付时间")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "确认收货时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("确认收货时间")
    private Date receiveTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setAddresssId(Long addresssId) {
        this.addresssId = addresssId;
    }

    public Long getAddresssId() {
        return addresssId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setUseIntegration(Long useIntegration) {
        this.useIntegration = useIntegration;
    }

    public Long getUseIntegration() {
        return useIntegration;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderSn", getOrderSn())
                .append("createTime", getCreateTime())
                .append("memberUsername", getMemberUsername())
                .append("totalAmount", getTotalAmount())
                .append("payAmount", getPayAmount())
                .append("payType", getPayType())
                .append("status", getStatus())
                .append("receiverName", getReceiverName())
                .append("receiverPhone", getReceiverPhone())
                .append("addresssId", getAddresssId())
                .append("note", getNote())
                .append("confirmStatus", getConfirmStatus())
                .append("deleteStatus", getDeleteStatus())
                .append("useIntegration", getUseIntegration())
                .append("paymentTime", getPaymentTime())
                .append("deliveryTime", getDeliveryTime())
                .append("receiveTime", getReceiveTime())
                .toString();
    }
}
