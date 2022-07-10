package com.qgj.product.service.impl;

import java.util.List;

import com.qgj.common.core.domain.ListImgsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.product.mapper.ProductImgMapper;
import com.qgj.common.core.domain.entity.ProductImg;
import com.qgj.product.service.IProductImgService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品图片Service业务层处理
 *
 * @author qgj
 * @date 2022-04-20
 */
@Service
public class ProductImgServiceImpl implements IProductImgService
{
    @Autowired
    private ProductImgMapper productImgMapper;

    /**
     * 查询商品图片
     *
     * @param id 商品图片主键
     * @return 商品图片
     */
    @Override
    public ProductImg selectProductImgById(Long id)
    {


        return productImgMapper.selectProductImgById(id);
    }

    /**
     * 查询商品图片列表
     *
     * @param productImg 商品图片
     * @return 商品图片
     */
    @Override
    public List<ProductImg> selectProductImgList(ProductImg productImg)
    {
        return productImgMapper.selectProductImgList(productImg);
    }

    /**
     * 新增商品图片
     *
     * @param productImg 商品图片
     * @return 结果
     */
    @Override
    public int insertProductImg(ProductImg productImg)
    {
        return productImgMapper.insertProductImg(productImg);
    }

    /**
     * 修改商品图片
     *
     * @param productImg 商品图片
     * @return 结果
     */
    @Override
    public int updateProductImg(ProductImg productImg)
    {
        return productImgMapper.updateProductImg(productImg);
    }

    /**
     * 批量删除商品图片
     *
     * @param ids 需要删除的商品图片主键
     * @return 结果
     */
    @Override
    public int deleteProductImgByIds(Long[] ids)
    {
        return productImgMapper.deleteProductImgByIds(ids);
    }

    /**
     * 删除商品图片信息
     *
     * @param id 商品图片主键
     * @return 结果
     */
    @Override
    public int deleteProductImgById(Long id)
    {
        return productImgMapper.deleteProductImgById(id);
    }


    @Override
    public int insertProductImgs(List<ProductImg> productImgs) {
        return productImgMapper.insertProductImgs(productImgs);
    }

    @Override
    public int deleteProductImgByProductId(Long productId) {
        return productImgMapper.deleteProductImgByProductId(productId);
    }

    @Override
    @Transactional
    public int updateProduct(ListImgsEntity listimsg) {
        deleteProductImgByProductId(listimsg.getProductId());
        listimsg.getImgs().stream().forEach(e ->{             //修改商品的id
            e.setProductId(listimsg.getProductId());
        });
        return  insertProductImgs(listimsg.getImgs());
    }

    @Override
    public List<ProductImg> selectProductImgByPid(Long productId) {
        return productImgMapper.selectProductImgByPid(productId);
    }
}
