package com.qgj.web.controller.banner;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.qgj.common.core.domain.entity.HomeBanner;
import com.qgj.common.core.redis.RedisCache;
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
import com.qgj.home.service.IHomeBannerService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 主页轮播Controller
 *
 * @author qgj
 * @date 2022-04-18
 */
@RestController
@Api(tags = "主页轮播 接口")
@RequestMapping("/banner")
public class HomeBannerController extends BaseController {
    @Autowired
    private IHomeBannerService homeBannerService;

    @Resource
    RedisCache redisCache;

    /**
     * 查询主页轮播列表
     */
    @ApiOperation(value = "查询主页轮播列表")
    @PreAuthorize("@ss.hasPermi('home:banner:list')")

    @GetMapping("/banner/list")
    public TableDataInfo list(HomeBanner homeBanner) {
        startPage();
        List<HomeBanner> list = homeBannerService.selectHomeBannerList(homeBanner);
        return getDataTable(list);
    }

    /**
     * 导出主页轮播列表
     */
    @ApiOperation(value = "导出主页轮播列表")

    @PreAuthorize("@ss.hasPermi('home:banner:export')")
    @Log(title = "主页轮播", businessType = BusinessType.EXPORT)
    @PostMapping("/banner/export")
    public void export(HttpServletResponse response, HomeBanner homeBanner) {
        List<HomeBanner> list = homeBannerService.selectHomeBannerList(homeBanner);
        ExcelUtil<HomeBanner> util = new ExcelUtil<HomeBanner>(HomeBanner.class);
        util.exportExcel(response, list, "主页轮播数据");
    }

    /**
     * 获取主页轮播详细信息
     */
    @ApiOperation(value = "获取主页轮播详细信息")
    @PreAuthorize("@ss.hasPermi('home:banner:query')")
    @GetMapping(value = "banner//{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(homeBannerService.selectHomeBannerById(id));
    }

    /**
     * 新增主页轮播
     */
    @ApiOperation(value = "新增主页轮播")
    @PreAuthorize("@ss.hasPermi('home:banner:add')")
    @Log(title = "主页轮播", businessType = BusinessType.INSERT)
    @PostMapping("/banner")
    public AjaxResult add(@RequestBody HomeBanner homeBanner) {
        redisCache.deleteObject(IndexController.CACHE_KEY_BANNER);
        return toAjax(homeBannerService.insertHomeBanner(homeBanner));
    }

    /**
     * 修改主页轮播
     */
    @ApiOperation(value = "修改主页轮播")
    @PreAuthorize("@ss.hasPermi('home:banner:edit')")
    @Log(title = "主页轮播", businessType = BusinessType.UPDATE)
    @PutMapping("/banner")
    public AjaxResult edit(@RequestBody HomeBanner homeBanner) {
        redisCache.deleteObject(IndexController.CACHE_KEY_BANNER);

        return toAjax(homeBannerService.updateHomeBanner(homeBanner));
    }

    /**
     * 删除主页轮播
     */
    @ApiOperation(value = "删除主页轮播")
    @PreAuthorize("@ss.hasPermi('home:banner:remove')")
    @Log(title = "主页轮播", businessType = BusinessType.DELETE)
    @DeleteMapping("/banner/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        redisCache.deleteObject(IndexController.CACHE_KEY_BANNER);
        return toAjax(homeBannerService.deleteHomeBannerByIds(ids));
    }
}
