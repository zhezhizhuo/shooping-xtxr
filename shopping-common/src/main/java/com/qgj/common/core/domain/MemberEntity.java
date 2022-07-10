package com.qgj.common.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qgj.common.core.domain.entity.SysUser;


public class MemberEntity {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("avatar")
    private String avatar;

    public MemberEntity() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
