package com.qgj.common.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class CategorysEntity  implements Serializable {
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

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("layer")
    private Integer layer;
    @JsonProperty("parent")
    private String parent;
}
