package com.qgj.category.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qgj.common.core.domain.CategoryTreeEntity;
import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.TreeSelect;
import com.qgj.common.core.domain.entity.ChartData;
import com.qgj.common.core.domain.entity.SysMenu;
import com.qgj.common.utils.DateUtils;
import com.qgj.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qgj.category.mapper.CategoryBeanMapper;
import com.qgj.common.core.domain.entity.CategoryBean;
import com.qgj.category.service.ICategoryBeanService;

import javax.annotation.Resource;

/**
 * 商品类目Service业务层处理
 *
 * @author qgj
 * @date 2022-04-18
 */
@Service
public class CategoryBeanServiceImpl implements ICategoryBeanService
{
    @Resource
    private CategoryBeanMapper categoryBeanMapper;

    @Resource
    private IProductService productService;
    /**
     * 查询商品类目
     *
     * @param categoryId 商品类目主键
     * @return 商品类目
     */
    @Override
    public CategoryBean selectCategoryBeanByCategoryId(Long categoryId)
    {
        return categoryBeanMapper.selectCategoryBeanByCategoryId(categoryId);
    }

    /**
     * 查询商品类目列表
     *
     * @param categoryBean 商品类目
     * @return 商品类目
     */
    @Override
    public List<CategoryBean> selectCategoryBeanList(CategoryBean categoryBean)
    {
        return categoryBeanMapper.selectCategoryBeanList(categoryBean);
    }

    /**
     * 新增商品类目
     *
     * @param categoryBean 商品类目
     * @return 结果
     */
    @Override
    public int insertCategoryBean(CategoryBean categoryBean)
    {
        categoryBean.setCreateTime(DateUtils.getNowDate());
        return categoryBeanMapper.insertCategoryBean(categoryBean);
    }

    /**
     * 修改商品类目
     *
     * @param categoryBean 商品类目
     * @return 结果
     */
    @Override
    public int updateCategoryBean(CategoryBean categoryBean)
    {
        categoryBean.setUpdateTime(DateUtils.getNowDate());
        return categoryBeanMapper.updateCategoryBean(categoryBean);
    }

    /**
     * 批量删除商品类目
     *
     * @param categoryIds 需要删除的商品类目主键
     * @return 结果
     */
    @Override
    public int deleteCategoryBeanByCategoryIds(Long[] categoryIds)
    {
        //删除改分类下的二级分类
        categoryBeanMapper.deleteCategoryNodesByCid(categoryIds);
        return categoryBeanMapper.deleteCategoryBeanByCategoryIds(categoryIds);
    }

    /**
     * 删除商品类目信息
     *
     * @param categoryId 商品类目主键
     * @return 结果
     */
    @Override
    public int deleteCategoryBeanByCategoryId(Long categoryId)
    {
        return categoryBeanMapper.deleteCategoryBeanByCategoryId(categoryId);
    }

    @Override
    public List<CategoryBean> selectCategoryBeanListByCategoryId(Long id) {
        return categoryBeanMapper.selectCategoryBeanListByCategoryId(id);
    }

    @Override
    public List<TreeSelect> buildCategorySelect(List<CategoryBean> categorys) {
        List<CategoryBean> category =buildCategoryTreeSelect(categorys);
        return category.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<CategoryTreeEntity> buildCategoryCategoryById(List<CategoryBean> categorys) {
        //查询分类

        //查询分类子商品的所有商品
        return null;
    }

    @Override
    public List<CategoryBean> selectCategoryHead() {
        return categoryBeanMapper.selectCategoryHead();
    }

    @Override
    public List<CategoryBean> selectCategoryGoods() {
        return null;
    }

    @Override
    public List<CategoryTreeEntity> buildCategoryHead(List<CategoryBean> categorys) {

        List<CategoryBean> category =buildCategoryHeads(categorys);

        return category.stream().map(CategoryTreeEntity::new).collect(Collectors.toList());
    }

    @Override
    public List<CategoryTreeEntity> buildCategoryGoods(List<CategoryBean> categorys) {
        List<CategoryBean> category =buildCategoryGoodsList(categorys);
        return category.stream().map(CategoryTreeEntity::new).collect(Collectors.toList());
    }

    private List<CategoryBean> buildCategoryGoodsList(List<CategoryBean> categorys) {
        List<CategoryBean> returns = new ArrayList<>();
        List<Long> temp = new ArrayList<Long>();
        for (CategoryBean categoryBean : categorys)
        {
            temp.add(categoryBean.getCategoryId());
        }
        for (Iterator<CategoryBean> iterator = categorys.iterator(); iterator.hasNext();)
        {
            CategoryBean categoryBean = (CategoryBean) iterator.next();

            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!temp.contains(Long.valueOf(categoryBean.getParentId())))
            {
                categoryBean.setGoods(getGoods(categoryBean.getCategoryId()));
                recursionFn(categorys, categoryBean);
                returns.add(categoryBean);
            }
        }
        if (returns.isEmpty())
        {
            returns = categorys;
        }
        return returns;
    }

    private List<CategoryBean> buildCategoryHeads(List<CategoryBean> categorys) {
        List<CategoryBean> returns = new ArrayList<>();
        List<Long> temp = new ArrayList<Long>();
        for (CategoryBean categoryBean : categorys)
        {
            temp.add(categoryBean.getCategoryId());
        }
        for (Iterator<CategoryBean> iterator = categorys.iterator(); iterator.hasNext();)
        {
            CategoryBean categoryBean = (CategoryBean) iterator.next();
                     /*
            根据分类id查询该分类下的所有商品
             */
              categoryBean.setGoods(getGoods(categoryBean.getCategoryId()));
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!temp.contains(Long.valueOf(categoryBean.getParentId())))
            {
                recursionFn(categorys, categoryBean);
                returns.add(categoryBean);
            }
        }
        if (returns.isEmpty())
        {
            returns = categorys;
        }
        return returns;
    }

    private List<Goods> getGoods(Long categoryId) {
          return   productService.selectProductListByCategoryId(categoryId).stream().map(Goods::new).collect(Collectors.toList());
    }


    private List<CategoryBean> buildCategoryTreeSelect(List<CategoryBean> categorys) {
        List<CategoryBean> returnList = new ArrayList<CategoryBean>();
        List<Long> tempList = new ArrayList<Long>();
        for (CategoryBean categoryBean : categorys)
        {
            tempList.add(categoryBean.getCategoryId());
        }
        for (Iterator<CategoryBean> iterator = categorys.iterator(); iterator.hasNext();)
        {
            CategoryBean categoryBean = (CategoryBean) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(Long.valueOf(categoryBean.getParentId())))
            {
                recursionFn(categorys, categoryBean);
                returnList.add(categoryBean);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = categorys;
        }
        return returnList;
    }

    private List<CategoryBean> recursionFn(List<CategoryBean> categorys, CategoryBean categoryBean) {
    List<CategoryBean> childList=      getChildList(categorys, categoryBean);
    categoryBean.setChildren(childList);

        for (CategoryBean bean : childList) {
                    if(hasChild(childList,bean)){
                        recursionFn(categorys,bean);
                    }
        }
        return childList;
    }

    private boolean hasChild(List<CategoryBean> childList, CategoryBean bean) {
        return getChildList(childList, bean).size() > 0;
    }

    @Override
    public List<ChartData> getCharData() {
        return categoryBeanMapper.getCharData();
    }

    private List<CategoryBean> getChildList(List<CategoryBean> categorys, CategoryBean categoryBean) {
        List<CategoryBean> tlist = new ArrayList<CategoryBean>();
        Iterator<CategoryBean> it = categorys.iterator();
        while (it.hasNext())
        {
            CategoryBean n = (CategoryBean) it.next();
            if (n.getParentId().longValue() == categoryBean.getCategoryId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
}
