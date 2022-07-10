package com.qgj.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.qgj.common.annotation.Excel;
import com.qgj.common.core.domain.BaseEntity;

/**
 * 订单商品对象 order_item
 *
 * @author qgj
 * @date 2022-04-26
 */
@ApiModel("订单商品")
public class OrderItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("")
    private Long id;

    /** 订单id */
    @Excel(name = "订单id")
    @ApiModelProperty("订单id")
    private Long orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    @ApiModelProperty("订单编号")
    private String orderSn;

    /** 商品id */
    @Excel(name = "商品id")
    @ApiModelProperty("商品id")
    private Long productId;

    /** 购买数量 */
    @Excel(name = "购买数量")
    @ApiModelProperty("购买数量")
    private Long productQuantity;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setOrderSn(String orderSn)
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn()
    {
        return orderSn;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setProductQuantity(Long productQuantity)
    {
        this.productQuantity = productQuantity;
    }

    public Long getProductQuantity()
    {
        return productQuantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderSn", getOrderSn())
            .append("productId", getProductId())
            .append("productQuantity", getProductQuantity())
            .toString();
    }
}
