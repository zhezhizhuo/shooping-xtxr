package com.qgj.product.mapper;

import java.util.List;

import com.qgj.common.core.domain.CategorysEntity;
import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.GoodsEntity;
import com.qgj.common.core.domain.entity.Product;
import com.qgj.common.core.domain.entity.ProductHotSort;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息Mapper接口
 *
 * @author qgj
 * @date 2022-04-19
 */

public interface ProductMapper
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
    public List<Product> selectProductListNews();

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
     * 删除商品信息
     *
     * @param productId 商品信息主键
     * @return 结果
     */
    public int deleteProductByProductId(Long productId);

    /**
     * 批量删除商品信息
     *
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductByProductIds(Long[] productIds);

    List<ProductHotSort> selectProductHotSort();

    List<Product> selectProductListByCategoryId(Long id);

    GoodsEntity selectGoodInfoByProductId(Long id);

    List<Goods> selectGoodsHotByDay();

    List<Goods> selectGoodsListByCategoryId(@Param("cid") Long categoryId,@Param("limit") Integer limit);

    CategorysEntity selectProductParent(Long id);

    CategorysEntity selectCategorParent(String id);
}
