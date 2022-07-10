package com.qgj.common.core.domain.entity;

import io.swagger.annotations.ApiModel;

/**
 * @author zhiZhou
 * @createDate: 2022/5/29 19:00
 */
@ApiModel("char数据图")
public class ChartData {

    private Integer value;
    private String name;


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
