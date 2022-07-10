package com.qgj.web.controller.product;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.qgj.common.core.domain.ListImgsEntity;
import com.qgj.common.core.domain.entity.Product;

import com.qgj.common.core.domain.entity.ProductHotSort;
import com.qgj.common.core.domain.entity.ProductImg;
import com.qgj.common.core.redis.RedisCache;
import com.qgj.product.service.IProductImgService;
import com.qgj.web.controller.home.IndexController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.qgj.common.annotation.Log;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.enums.BusinessType;

import com.qgj.product.service.IProductService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 *
 * @author qgj
 * @date 2022-04-19
 */
@RestController
@Api( tags = "商品信息 接口")
@RequestMapping("/product/product")
public class ProductController extends BaseController {
    @Resource
    private IProductService productService;
    @Resource
    private IProductImgService productImgService;
    @Resource
    private  IProductImgService imgService;

    @Resource
    private RedisCache redisCache;

    public  void delCache(){
        redisCache.deleteObject(IndexController.CACHE_KEY_HEAD);
        redisCache.deleteObject(IndexController.CACHE_KEY_GOODS);
    }
    /**
     * 查询商品信息列表
     */
    @ApiOperation(value = "查询商品信息列表")
    @PreAuthorize("@ss.hasPermi('product:product:list')")
     
    @GetMapping("/list")
        public TableDataInfo list(Product product) {
            startPage();
            List<Product> list = productService.selectProductList(product);
            return getDataTable(list);
        }

    /**
     * 获取商品热度
     */
    @ApiOperation(value = "查询商品热度列表")
    @PreAuthorize("@ss.hasPermi('product:product:hot')")
     
    @GetMapping("/hot")
    public TableDataInfo hots() {
        startPage();

        List<ProductHotSort> list  = productService.selectProductHotSort();
        logger.error("商品列表==>{}",list);
        return getDataTable(list);
    }






    /**
     * 导出商品信息列表
     */
    @ApiOperation(value = "导出商品信息列表")
    @PreAuthorize("@ss.hasPermi('product:product:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Product product) {
        List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product. class);
        util.exportExcel(response, list, "商品信息数据");
    }

    //根据商品id 查询 所有图片

    @ApiOperation(value = "根据商品id 查询 所有图片")
    @PreAuthorize("@ss.hasPermi('product:product:query')")
    @GetMapping(value = "/img/{productId}")
    public AjaxResult getImgInfoByProductId(@PathVariable("productId") Long productId) {
        List<ProductImg> productImgs = imgService.selectProductImgByPid(productId);
        return AjaxResult.success(productImgs);
    }

    //删除商品图片
    /**
     * 删除商品图片
     */
    @ApiOperation(value = "删除商品图片")
    @PreAuthorize("@ss.hasPermi('product:img:remove')")
    @Log(title = "删除商品图片", businessType = BusinessType.DELETE)
    @DeleteMapping("/img/{ids}")
    public AjaxResult removeImg(@PathVariable Long[] ids) {
        delCache();
        return toAjax(productImgService.deleteProductImgByIds(ids));
    }


    /**
     * 新增商品信息
     */
    @ApiOperation(value = "批量添加 添加商品图片")
    @ApiOperationSupport(includeParameters = {"ud","createBy","createTime","updateTime","searchValue",})
    @PreAuthorize("@ss.hasPermi('product:product:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping("/imgs")
    public AjaxResult addImgs(@RequestBody ListImgsEntity listimsg) {
        //删除该商品的所有图片然后添加
        delCache();
        return toAjax( imgService.updateProduct(listimsg));
    }


    /**
     * 获取商品信息详细信息
     */
    @ApiOperation(value = "获取商品信息详细信息")
    @PreAuthorize("@ss.hasPermi('product:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId) {
        return AjaxResult.success(productService.selectProductByProductId(productId));
    }

    /**
     * 新增商品信息
     */
    @ApiOperation(value = "新增商品信息")
    @ApiOperationSupport(includeParameters = {"ud","createBy","createTime","updateTime","searchValue",})
    @PreAuthorize("@ss.hasPermi('product:product:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Product product) {
        delCache();

        return toAjax(productService.insertProduct(product));
    }

    /**
     * 修改商品信息
     */
    @ApiOperation(value = "修改商品信息")
    
    @PreAuthorize("@ss.hasPermi('product:product:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Product product) {
        delCache();

        return toAjax(productService.updateProduct(product));
    }



    /**
     * 删除商品信息
     */
    @ApiOperation(value = "删除商品信息")
    @PreAuthorize("@ss.hasPermi('product:product:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds) {
        delCache();

        return toAjax(productService.deleteProductByProductIds(productIds));
    }
}
