package com.qgj.category.mapper;

import java.util.List;
import com.qgj.common.core.domain.entity.CategoryBean;
import com.qgj.common.core.domain.entity.ChartData;

/**
 * 商品类目Mapper接口
 *
 * @author qgj
 * @date 2022-04-18
 */

public interface CategoryBeanMapper
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
     * 删除商品类目
     *
     * @param categoryId 商品类目主键
     * @return 结果
     */
    public int deleteCategoryBeanByCategoryId(Long categoryId);

    /**
     * 批量删除商品类目
     *
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryBeanByCategoryIds(Long[] categoryIds);

    List<CategoryBean> selectCategoryHead();

    List<CategoryBean> selectCategoryBeanListByCategoryId(Long id);

    int deleteCategoryNodesByCid(Long[] categoryIds);

    List<ChartData> getCharData();
}
