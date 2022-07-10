package com.qgj.common.core.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.io.Serializable;

@ApiModel("注册实体类")
public class RegisterModel implements Serializable {

    @ApiModelProperty(required = true,value = "用户名")
    private String account;
    @ApiModelProperty(required = true,value = "手机号码")
    private String mobile;
    @ApiModelProperty(required = true,value = "验证码")
    private String code;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 唯一标识
     */
    @ApiModelProperty(value = "唯一标识")
    private String uuid;
    @ApiModelProperty(required = true,value = "用户昵称")
    private String nick_name;
    @ApiModelProperty(required = true,value = "用户性别（0男 1女 2未知）")
    private Integer sex;

    @ApiModelProperty(required = true,value = "密码")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

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

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "account='" + account + '\'' +
                ", mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                '}';
    }
}
