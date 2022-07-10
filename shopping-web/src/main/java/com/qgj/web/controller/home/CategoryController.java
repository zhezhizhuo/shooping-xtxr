package com.qgj.web.controller.home;

import com.qgj.category.service.ICategoryBeanService;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.CategoryTreeEntity;
import com.qgj.common.core.domain.entity.CategoryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "一级分类 类目")
public class CategoryController extends BaseController {


    @Resource
    private ICategoryBeanService categoryBeanService;

    //根据id查询该分类下的所有子分类和子产品
    @GetMapping("/category")
    @ApiOperation(value = "一级类目-PC(包括推荐商品)", notes = "作者: Zhizhuo ")
    public AjaxResult getCategoryById(@RequestParam @ApiParam("一级类目ID") Long id) {
        //没有就查询数据库
        List<CategoryBean> categorys = categoryBeanService.selectCategoryBeanListByCategoryId(id);
        //把数据存入 redis
        List<CategoryTreeEntity> categoryTreeEntities = categoryBeanService.buildCategoryHead(categorys);
        categoryTreeEntities.get(0).setGoods(null);
        categoryTreeEntities.get(0).setPicture(null);
        return AjaxResult.success(categoryTreeEntities);
    }
}
