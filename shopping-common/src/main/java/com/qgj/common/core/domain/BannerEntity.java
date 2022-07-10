package com.qgj.common.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qgj.common.core.domain.entity.HomeBanner;

import java.io.Serializable;


public class BannerEntity implements Serializable {


    @JsonProperty("id")
    private Long id;
    @JsonProperty("imgUrl")
    private String imgUrl;
    @JsonProperty("hrefUrl")
    private String hrefUrl;
    @JsonProperty("type")
    private Long pid;

    public BannerEntity() {
    }

    public BannerEntity(HomeBanner homeBanner) {
        this.id = homeBanner.getId();
        this.imgUrl = homeBanner.getImgurl();
        this.hrefUrl = homeBanner.getHrefurl();
        this.pid = homeBanner.getPid();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
