package com.qgj.common.core.domain;

public class HotEntity {

        private Integer id;
        private String picture;
        private String title;
        private String alt;

    public HotEntity() {
    }

    public HotEntity(Integer id, String picture, String title, String alt) {
        this.id = id;
        this.picture = picture;
        this.title = title;
        this.alt = alt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
