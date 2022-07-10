package com.qgj.common.core.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.qgj.common.annotation.Excel;
import com.qgj.common.core.domain.BaseEntity;

/**
 * 商品信息对象 product_info
 *
 * @author qgj
 * @date 2022-04-19
 */
@ApiModel("商品信息")

public class Product extends BaseEntity
{

    private static final long serialVersionUID = 1L;


    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    /** 商品ID */
    @ApiModelProperty("")
    private Long productId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    @ApiModelProperty("商品名称")
    private String productName;

    /** 商品价格 */
    @Excel(name = "商品价格")
    @ApiModelProperty("商品价格")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    /** 商品状态 */
    @Excel(name = "商品状态")
    @ApiModelProperty("商品状态")
    private Integer pStatus;

    /** 商品描述 */
    @Excel(name = "商品描述")
    @ApiModelProperty("商品描述")
    private String descript;

    /** 所属类目 */
    @Excel(name = "所属类目")
    @ApiModelProperty("所属类目")
    private String categoryName;

    /** 类目ID */
    @Excel(name = "类目ID")
    @ApiModelProperty("类目ID")
    private Long categoryId;

    /** 商品图片 */
    @Excel(name = "商品图片")
    @ApiModelProperty("商品图片")
    private String picture;

    private List<String> mainPictures;

    public List<String> getMainPictures() {
        return mainPictures;
    }

    public void setMainPictures(List<String> mainPictures) {
        this.mainPictures = mainPictures;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setpStatus(Integer pStatus)
    {
        this.pStatus = pStatus;
    }

    public Integer getpStatus()
    {
        return pStatus;
    }
    public void setDescript(String descript)
    {
        this.descript = descript;
    }

    public String getDescript()
    {
        return descript;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getPicture()
    {
        return picture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("price", getPrice())
            .append("pStatus", getpStatus())
            .append("descript", getDescript())
            .append("categoryName", getCategoryName())
            .append("categoryId", getCategoryId())
            .append("createTime", getCreateTime())
            .append("picture", getPicture())
            .toString();
    }
}
