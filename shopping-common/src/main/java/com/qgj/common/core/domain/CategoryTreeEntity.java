package com.qgj.common.core.domain;

import com.qgj.common.core.domain.entity.CategoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryTreeEntity implements Serializable {

    private String id;

    private String name;

    private String picture;

    List<CategoryTreeEntity>  children;

    public CategoryTreeEntity() {
    }

    public CategoryTreeEntity(CategoryBean categoryBean) {
        this.id = String.valueOf(categoryBean.getCategoryId());
        this.name = categoryBean.getCategoryName();
        this.picture = categoryBean.getPicture();
        this.children = categoryBean.getChildren().stream().map(CategoryTreeEntity::new).collect(Collectors.toList());
        this.goods = categoryBean.getGoods();
    }

    List<Goods> goods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<CategoryTreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTreeEntity> children) {
        this.children = children;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
