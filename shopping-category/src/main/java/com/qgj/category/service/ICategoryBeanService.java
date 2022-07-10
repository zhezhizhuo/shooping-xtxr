package com.qgj.category.service;

import java.util.List;

import com.qgj.common.core.domain.CategoryTreeEntity;
import com.qgj.common.core.domain.TreeSelect;
import com.qgj.common.core.domain.entity.CategoryBean;
import com.qgj.common.core.domain.entity.ChartData;

/**
 * 商品类目Service接口
 *
 * @author qgj
 * @date 2022-04-18
 */
public interface ICategoryBeanService
{
    /**
     * 查询商品类目
     *
     * @param categoryId 商品类目主键
     * @return 商品类目
     */
    public CategoryBean selectCategoryBeanByCategoryId(Long categoryId);

    /**
     * 查询商品类目列表
     *
     * @param categoryBean 商品类目
     * @return 商品类目集合
     */
    public List<CategoryBean> selectCategoryBeanList(CategoryBean categoryBean);

    /**
     * 新增商品类目
     *
     * @param categoryBean 商品类目
     * @return 结果
     */
    public int insertCategoryBean(CategoryBean categoryBean);

    /**
     * 修改商品类目
     *
     * @param categoryBean 商品类目
     * @return 结果
     */
    public int updateCategoryBean(CategoryBean categoryBean);

    /**
     * 批量删除商品类目
     *
     * @param categoryIds 需要删除的商品类目主键集合
     * @return 结果
     */
    public int deleteCategoryBeanByCategoryIds(Long[] categoryIds);

    /**
     * 删除商品类目信息
     *
     * @param categoryId 商品类目主键
     * @return 结果
     */
    public int deleteCategoryBeanByCategoryId(Long categoryId);

    List<TreeSelect>  buildCategorySelect(List<CategoryBean> categorys);

    List<CategoryBean> selectCategoryHead();

    List<CategoryTreeEntity> buildCategoryHead(List<CategoryBean> categorys);

    List<CategoryBean> selectCategoryBeanListByCategoryId(Long id);

    List<CategoryTreeEntity> buildCategoryCategoryById(List<CategoryBean> categorys);

    List<CategoryBean> selectCategoryGoods();

    List<CategoryTreeEntity> buildCategoryGoods(List<CategoryBean> selectCategoryHead);

    List<ChartData> getCharData();
}
