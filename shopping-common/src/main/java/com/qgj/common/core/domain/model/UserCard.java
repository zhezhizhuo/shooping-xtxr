package com.qgj.common.core.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("购物车实体类")
public class UserCard  implements Serializable {

    @ApiModelProperty(required = true,value = "商品id")
    private Long id;
    @ApiModelProperty(required = true,value = "商品数量")
    private Long count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
