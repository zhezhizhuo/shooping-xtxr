package com.qgj.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qgj.common.annotation.Excel;
import com.qgj.common.core.domain.BaseEntity;

/**
 * 主页轮播对象 home_banner
 *
 * @author qgj
 * @date 2022-04-18
 */
@ApiModel("轮播图实体类")
public class HomeBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long pid;

    /** 跳转地址 */
    @Excel(name = "跳转地址")
    private String hrefurl;

    /** 序号 */
    @Excel(name = "序号")
    private Long sort;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imgurl;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPid(Long pid)
    {
        this.pid = pid;
    }

    public Long getPid()
    {
        return pid;
    }
    public void setHrefurl(String hrefurl)
    {
        this.hrefurl = hrefurl;
    }

    public String getHrefurl()
    {
        return hrefurl;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setImgurl(String imgurl)
    {
        this.imgurl = imgurl;
    }

    public String getImgurl()
    {
        return imgurl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("hrefurl", getHrefurl())
            .append("sort", getSort())
            .append("imgurl", getImgurl())
            .append("createTime", getCreateTime())
            .toString();
    }
}
