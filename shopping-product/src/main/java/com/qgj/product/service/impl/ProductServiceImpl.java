package com.qgj.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.qgj.common.core.domain.CategorysEntity;
import com.qgj.common.core.domain.CommentEntity;
import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.GoodsEntity;
import com.qgj.common.core.domain.entity.ProductHotSort;
import com.qgj.common.core.domain.entity.ProductImg;
import com.qgj.common.core.domain.entity.UserFoot;
import com.qgj.common.utils.DateUtils;
import com.qgj.common.utils.SecurityUtils;
import com.qgj.foot.service.IUserFootService;
import com.qgj.product.service.IProductCommentService;
import com.qgj.product.service.IProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.product.mapper.ProductMapper;
import com.qgj.common.core.domain.entity.Product;
import com.qgj.product.service.IProductService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 商品信息Service业务层处理
 *
 * @author qgj
 * @date 2022-04-19
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Resource
    private IProductImgService productImgService;


    @Resource
    private IProductCommentService commentService;

    /**
     * 查询商品信息
     *
     * @param productId 商品信息主键
     * @return 商品信息
     */
    @Override
    public Product selectProductByProductId(Long productId) {
        Product product = productMapper.selectProductByProductId(productId);
        product.setMainPictures(getMainPictures(product.getProductId()));
        return product;
    }

    /**
     * 查询商品信息列表
     *
     * @param product 商品信息
     * @return 商品信息
     */
    @Override
    public List<Product> selectProductList(Product product) {
        //这里查询多个图片
        List<Product> products = productMapper.selectProductList(product);
        for (Product p : products) {
            p.setMainPictures(getMainPictures(p.getProductId()));
        }
        return products;
    }

    private List<String> getMainPictures(Long productId) {
        List<String> list = new ArrayList<>();
        productImgService.selectProductImgByPid(productId).stream().forEach(e -> list.add(e.getImg()));
//        productImgService.selectProductImgByPid(productId).stream().map(e)
        return list;
    }

    /**
     * 新增商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    @Override
    public int insertProduct(Product product) {
        product.setCreateTime(DateUtils.getNowDate());
        return productMapper.insertProduct(product);
    }

    /**
     * 修改商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    /**
     * 批量删除商品信息
     *
     * @param productIds 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductByProductIds(Long[] productIds) {
        return productMapper.deleteProductByProductIds(productIds);
    }

    /**
     * 删除商品信息信息
     *
     * @param productId 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductByProductId(Long productId) {
        return productMapper.deleteProductByProductId(productId);
    }

    @Override
    public List<Product> selectProductListNew() {
        return productMapper.selectProductListNews();
    }

    @Override
    public List<ProductHotSort> selectProductHotSort() {
        return productMapper.selectProductHotSort();
    }

    @Override
    public List<Product> selectProductListByCategoryId(Long id) {
        return productMapper.selectProductListByCategoryId(id);
    }


    @Override
    public GoodsEntity queryProductInfoById(Long id) {

        //查询
        GoodsEntity good = productMapper.selectGoodInfoByProductId(id);
        //设置图片
        List<ProductImg> productImgs = productImgService.selectProductImgByPid(id);
        List<String> imgs = productImgs.stream().map(ProductImg::getImg).collect(Collectors.toList());
        good.setMainPictures(imgs);
        //热门商品
//        List<ProductHotSort> productHotSorts = selectProductHotSort();
//       List<Goods> goods = productHotSorts.stream().map(e -> productMapper.selectProductByProductId(e.getProductId())).map(Goods::new).collect(Collectors.toList());
        List<Goods> goods = productMapper.selectGoodsHotByDay();
        //寻找商品的父亲id
        good.setCategories(getCategorise(id));
        good.setHotByDay(goods);
        return good;
    }

    private List<CategorysEntity> getCategorise(Long id) {
        List<CategorysEntity> list = new ArrayList<>();
        //查看该商品分分类
        CategorysEntity categorysEntity = productMapper.selectProductParent(id);

        list.add(categorysEntity);
        CategorysEntity categorys2 = productMapper.selectCategorParent(categorysEntity.getParent());
        list.add(categorys2);
        //该商品是否还有分类
        return list;
    }

    @Override
    public List<Goods> queryRelevantInfoById(Long id, Integer limit) {
        Product product = productMapper.selectProductByProductId(id);
        //根据分类名找到同类商品
        return productMapper.selectGoodsListByCategoryId(product.getCategoryId(), limit);
    }


    @Override
    public String queryProductCommentsByPd(Long id) {
        return null;
    }

    @Override
    public List<CommentEntity> selectProductCommentByPid(Long id) {

        List<CommentEntity> commentEntities = commentService.selectProductCommentByPid(id);
        try {
            commentEntities.stream().forEach( item ->{
                //查看每一个评论是否 用户已经评论
                Long userId = SecurityUtils.getUserId();
                Integer commid = item.getId();
                if (commentService.selectCommByUIdAndComId(userId,commid) !=0){            //用户评论了
                    item.setLike(true);
                }
            });
            return  commentEntities;
        } catch (Exception e){
            e.printStackTrace();
            return commentEntities ;
        }
    }

    @Override
    public List<CommentEntity> selectProductCommentByPidHot(Long id) {
        List<CommentEntity> commentEntities = commentService.selectProductCommentByPidHot(id);
        try {
            commentEntities.stream().forEach( item ->{
                //查看每一个评论是否 用户已经评论
                Long userId = SecurityUtils.getUserId();
                Integer commid = item.getId();
                if (commentService.selectCommByUIdAndComId(userId,commid) !=0){            //用户评论了
                    item.setLike(true);
                }
            });
            return  commentEntities;
        } catch (Exception e){
            e.printStackTrace();
            return commentEntities ;
        }
    }
}
