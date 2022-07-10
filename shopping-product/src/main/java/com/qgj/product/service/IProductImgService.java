package com.qgj.product.service;

import java.util.List;

import com.qgj.common.core.domain.CategorysEntity;
import com.qgj.common.core.domain.ListImgsEntity;
import com.qgj.common.core.domain.entity.ProductImg;

/**
 * 商品图片Service接口
 *
 * @author qgj
 * @date 2022-04-20
 */
public interface IProductImgService
{
    /**
     * 查询商品图片
     *
     * @param id 商品图片主键
     * @return 商品图片
     */
    public ProductImg selectProductImgById(Long id);

    /**
     * 查询商品图片列表
     *
     * @param productImg 商品图片
     * @return 商品图片集合
     */
    public List<ProductImg> selectProductImgList(ProductImg productImg);

    /**
     * 新增商品图片
     *
     * @param productImg 商品图片
     * @return 结果
     */
    public int insertProductImg(ProductImg productImg);

    /**
     * 修改商品图片
     *
     * @param productImg 商品图片
     * @return 结果
     */
    public int updateProductImg(ProductImg productImg);

    /**
     * 批量删除商品图片
     *
     * @param ids 需要删除的商品图片主键集合
     * @return 结果
     */
    public int deleteProductImgByIds(Long[] ids);

    /**
     * 删除商品图片信息
     *
     * @param id 商品图片主键
     * @return 结果
     */
    public int deleteProductImgById(Long id);

    List<ProductImg> selectProductImgByPid(Long productId);

    int insertProductImgs(List<ProductImg> productImgs);

    int deleteProductImgByProductId(Long productId);

    int updateProduct(ListImgsEntity listimsg);


}
