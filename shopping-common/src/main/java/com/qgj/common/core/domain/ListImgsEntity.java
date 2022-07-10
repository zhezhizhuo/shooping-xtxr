package com.qgj.common.core.domain;

import com.qgj.common.core.domain.entity.ProductImg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("商品图片集合")
public class ListImgsEntity implements Serializable {

    @ApiModelProperty("商品图片多个地址")
     private List<ProductImg> imgs;
    @ApiModelProperty("商品id")
        private Long productId;

    public List<ProductImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<ProductImg> imgs) {
        this.imgs = imgs;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
