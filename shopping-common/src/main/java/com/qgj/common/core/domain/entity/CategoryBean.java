package com.qgj.common.core.domain.entity;

import com.qgj.common.core.domain.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qgj.common.annotation.Excel;
import com.qgj.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品类目对象 product_category
 *
 * @author qgj
 * @date 2022-04-18
 */
@ApiModel("商品类目")
public class CategoryBean extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    @ApiModelProperty("分类ID")
    private Long categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    @ApiModelProperty("分类名称")
    private String categoryName;

    /** 父ID */
    @Excel(name = "父ID")
    @ApiModelProperty("父ID")
    private Long parentId;

    /** 排序 */
    @Excel(name = "排序")
    @ApiModelProperty("排序")
    private String sort;

    /** 0 表示正常 1表示下架 */
    @Excel(name = "0 表示正常 1表示下架")
    @ApiModelProperty("0 表示正常 1表示下架")
    private Integer pStatus;

    /** 分类图片 */
    @Excel(name = "分类图片")
    @ApiModelProperty("分类图片")
    private String picture;

    /** 商品总数 */
    @Excel(name = "商品总数")
    @ApiModelProperty("商品总数")
    private String total;

    private List<Goods> goods;


    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    /** 子分类 */
    private List<CategoryBean> children = new ArrayList<CategoryBean>();
    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getSort()
    {
        return sort;
    }
    public void setpStatus(Integer pStatus)
    {
        this.pStatus = pStatus;
    }

    public Integer getpStatus()
    {
        return pStatus;
    }
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public String getPicture()
    {
        return picture;
    }
    public void setTotal(String total)
    {
        this.total = total;
    }

    public String getTotal()
    {
        return total;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("categoryId", getCategoryId())
                .append("categoryName", getCategoryName())
                .append("parentId", getParentId())
                .append("sort", getSort())
                .append("pStatus", getpStatus())
                .append("picture", getPicture())
                .append("updateTime", getUpdateTime())
                .append("createTime", getCreateTime())
                .append("total", getTotal())
                .toString();
    }

    public List<CategoryBean> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryBean> children) {
        this.children = children;
    }
}
