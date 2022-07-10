package com.qgj.product.service;

import java.util.List;

import com.qgj.common.core.domain.CommentEntity;
import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.GoodsEntity;
import com.qgj.common.core.domain.entity.Product;
import com.qgj.common.core.domain.entity.ProductHotSort;

/**
 * 商品信息Service接口
 *
 * @author qgj
 * @date 2022-04-19
 */
public interface IProductService
{
    /**
     * 查询商品信息
     *
     * @param productId 商品信息主键
     * @return 商品信息
     */
    public Product selectProductByProductId(Long productId);

    /**
     * 查询商品信息列表
     *
     * @param product 商品信息
     * @return 商品信息集合
     */
    public List<Product> selectProductList(Product product);

    /**
     * 新增商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    public int insertProduct(Product product);

    /**
     * 修改商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    public int updateProduct(Product product);

    /**
     * 批量删除商品信息
     *
     * @param productIds 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteProductByProductIds(Long[] productIds);

    /**
     * 删除商品信息信息
     *
     * @param productId 商品信息主键
     * @return 结果
     */
    public int deleteProductByProductId(Long productId);

    List<Product> selectProductListNew();

    List<ProductHotSort> selectProductHotSort();

    List<Product> selectProductListByCategoryId(Long id);

    GoodsEntity queryProductInfoById(Long id);

    List<Goods> queryRelevantInfoById(Long id, Integer limit);

    String queryProductCommentsByPd(Long id);

    List<CommentEntity> selectProductCommentByPid(Long id);

    List<CommentEntity> selectProductCommentByPidHot(Long id);
}
