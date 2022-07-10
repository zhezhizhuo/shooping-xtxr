package com.qgj.common.core.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("PC手机号码登录 实体类")
public class PhoneLoginBody  implements Serializable {


    @ApiModelProperty(required = true,value = "手机号码")
    private String mobile;

    @ApiModelProperty(required = true,value = "验证码")
    private String code;

    @ApiModelProperty(value = "唯一标识")
    private String uuid;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
