package com.qgj.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.qgj.common.annotation.Excel;
import com.qgj.common.core.domain.BaseEntity;

/**
 * 商品图片对象 product_img
 *
 * @author qgj
 * @date 2022-04-20
 */
@ApiModel("商品图片")
public class ProductImg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("")
    private Long id;

    /** 商品id */
    @Excel(name = "商品id")
    @ApiModelProperty("商品id")
    private Long productId;

    /** 图片路径 */
    @Excel(name = "图片路径")
    @ApiModelProperty("图片路径")
    private String img;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd  HH:mm:ss")
    @ApiModelProperty("添加时间")
    private Date addTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }
    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("img", getImg())
            .append("addTime", getAddTime())
            .toString();
    }
}
