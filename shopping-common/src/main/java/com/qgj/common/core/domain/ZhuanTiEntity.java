package com.qgj.common.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class ZhuanTiEntity implements Serializable {

    @JsonProperty("creator")
    private String creator;
    @JsonProperty("isDelete")
    private Integer isDelete;
    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("updateTime")
    private String updateTime;
    @JsonProperty("id")
    private String id;
    @JsonProperty("classificationId")
    private String classificationId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("lowestPrice")
    private Double lowestPrice;
    @JsonProperty("cover")
    private String cover;
    @JsonProperty("detailsUrl")
    private String detailsUrl;
    @JsonProperty("collectNum")
    private Integer collectNum;
    @JsonProperty("viewNum")
    private Integer viewNum;
    @JsonProperty("replyNum")
    private Integer replyNum;

    public ZhuanTiEntity() {
    }

    public ZhuanTiEntity(String creator, Integer isDelete, String createTime, String updateTime, String id, String classificationId, String title, String summary, Double lowestPrice, String cover, String detailsUrl, Integer collectNum, Integer viewNum, Integer replyNum) {
        this.creator = creator;
        this.isDelete = isDelete;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.id = id;
        this.classificationId = classificationId;
        this.title = title;
        this.summary = summary;
        this.lowestPrice = lowestPrice;
        this.cover = cover;
        this.detailsUrl = detailsUrl;
        this.collectNum = collectNum;
        this.viewNum = viewNum;
        this.replyNum = replyNum;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }
}
