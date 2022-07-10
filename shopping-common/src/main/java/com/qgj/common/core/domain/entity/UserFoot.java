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
 * 用户足迹对象 user_foot
 *
 * @author qgj
 * @date 2022-04-21
 */
@ApiModel("用户足迹")
public class UserFoot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @ApiModelProperty("")
    private Long ftId;

    /**  */
    @Excel(name = "")
    @ApiModelProperty("")
    private Long pid;

    /**  */
    @Excel(name = "")
    @ApiModelProperty("用户id")
    private Long userId;


    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String productName;

    private String userName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Excel(name = "收藏时间")
    @ApiModelProperty("收藏时间")
    private Date time;

    public void setFtId(Long ftId)
    {
        this.ftId = ftId;
    }

    public Long getFtId()
    {
        return ftId;
    }
    public void setPid(Long pid)
    {
        this.pid = pid;
    }

    public Long getPid()
    {
        return pid;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }

    public Date getTime()
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ftId", getFtId())
            .append("pid", getPid())
            .append("userId", getUserId())
            .append("time", getTime())
            .toString();
    }
}
