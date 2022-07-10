package com.qgj.web.controller.category;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.qgj.common.core.domain.entity.CategoryBean;
import com.qgj.common.core.domain.entity.SysMenu;
import com.qgj.common.core.redis.RedisCache;
import com.qgj.web.controller.banner.HomeBannerController;
import com.qgj.web.controller.home.IndexController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qgj.common.annotation.Log;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.enums.BusinessType;
import com.qgj.category.service.ICategoryBeanService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 商品类目Controller
 *
 * @author qgj
 * @date 2022-04-18
 */
@RestController

@Api( tags = "商品类目 接口")
@RequestMapping("/product/category")
public class CategoryBeanController extends BaseController {
    @Autowired
    private ICategoryBeanService categoryBeanService;

    @Resource
    private RedisCache redisCache;

/**
 * 查询商品类目列表
 */
    @ApiOperation(value = "查询商品类目列表")
    @PreAuthorize("@ss.hasPermi('category:category:list')")
     
    @GetMapping("/list")
        public AjaxResult list(CategoryBean categoryBean) {
            List<CategoryBean> list = categoryBeanService.selectCategoryBeanList(categoryBean);
            return AjaxResult.success(list);
        }
    /**
     * 获取商品类目树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(CategoryBean categoryBean)
    {
        List<CategoryBean> categorys = categoryBeanService.selectCategoryBeanList(categoryBean);
        return AjaxResult.success(categoryBeanService.buildCategorySelect(categorys));
    }




    /**
     * 导出商品类目列表
     */
    @ApiOperation(value = "导出商品类目列表")

    @PreAuthorize("@ss.hasPermi('category:category:export')")
    @Log(title = "商品类目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryBean categoryBean) {
        List<CategoryBean> list = categoryBeanService.selectCategoryBeanList(categoryBean);
        ExcelUtil<CategoryBean> util = new ExcelUtil<CategoryBean>(CategoryBean. class);
        util.exportExcel(response, list, "商品类目数据");
    }

    /**
     * 获取商品类目详细信息
     */
    @ApiOperation(value = "获取商品类目详细信息")
    @PreAuthorize("@ss.hasPermi('category:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return AjaxResult.success(categoryBeanService.selectCategoryBeanByCategoryId(categoryId));
    }

    /**
     * 新增商品类目
     */
    @ApiOperation(value = "新增商品类目")
    @ApiOperationSupport(includeParameters = {"ud","createBy","createTime","updateTime","searchValue",})
    @PreAuthorize("@ss.hasPermi('category:category:add')")
    @Log(title = "商品类目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryBean categoryBean) {
        //删除缓存
        delCache();
        return toAjax(categoryBeanService.insertCategoryBean(categoryBean));
    }

    public  void delCache(){
        redisCache.deleteObject(IndexController.CACHE_KEY_HEAD);
        redisCache.deleteObject(IndexController.CACHE_KEY_GOODS);
    }
    /**
     * 修改商品类目
     */
    @ApiOperation(value = "修改商品类目")
    
    @PreAuthorize("@ss.hasPermi('category:category:edit')")
    @Log(title = "商品类目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryBean categoryBean) {
        delCache();

        return toAjax(categoryBeanService.updateCategoryBean(categoryBean));
    }

    /**
     * 获取每个分类占比的数据
     */
    @ApiOperation(value = "获取每个分类占比的数据")
    
    @PreAuthorize("@ss.hasPermi('category:category:edit')")
    @Log(title = "获取每个分类占比的数据", businessType = BusinessType.UPDATE)
    @GetMapping("chart")
    public AjaxResult chartData(){
        return AjaxResult.success(categoryBeanService.getCharData());
    }


    /**
     * 删除商品类目
     */
    @ApiOperation(value = "删除商品类目")
    @PreAuthorize("@ss.hasPermi('category:category:remove')")
    @Log(title = "商品类目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        delCache();

        return toAjax(categoryBeanService.deleteCategoryBeanByCategoryIds(categoryIds));
    }
}
