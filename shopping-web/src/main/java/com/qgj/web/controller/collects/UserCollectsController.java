package com.qgj.web.controller.collects;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.qgj.common.core.domain.entity.UserCollects;
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
import com.qgj.collects.service.IUserCollectsService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 用户收藏Controller
 *
 * @author qgj
 * @date 2022-04-19
 */
@RestController

@Api( tags = "用户收藏 接口")
@RequestMapping("/user/collects")
public class UserCollectsController extends BaseController {
    @Resource
    private IUserCollectsService userCollectsService;

    /**
     * 查询用户收藏列表
     */
    @ApiOperation(value = "查询用户收藏列表")
    @PreAuthorize("@ss.hasPermi('collects:collects:list')")

    @GetMapping("/list")
        public TableDataInfo list(UserCollects userCollects) {
            startPage();
            List<UserCollects> list = userCollectsService.selectUserCollectsList(userCollects);
            return getDataTable(list);
        }

    /**
     * 导出用户收藏列表
     */
    @ApiOperation(value = "导出用户收藏列表")

    @PreAuthorize("@ss.hasPermi('collects:collects:export')")
    @Log(title = "用户收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserCollects userCollects) {
        List<UserCollects> list = userCollectsService.selectUserCollectsList(userCollects);
        ExcelUtil<UserCollects> util = new ExcelUtil<UserCollects>(UserCollects. class);
        util.exportExcel(response, list, "用户收藏数据");
    }

    /**
     * 获取用户收藏详细信息
     */
    @ApiOperation(value = "获取用户收藏详细信息")
    @PreAuthorize("@ss.hasPermi('collects:collects:query')")
    @GetMapping(value = "/{colId}")
    public AjaxResult getInfo(@PathVariable("colId") Long colId) {
        return AjaxResult.success(userCollectsService.selectUserCollectsByColId(colId));
    }

    /**
     * 新增用户收藏
     */
    @ApiOperation(value = "新增用户收藏")
    @PreAuthorize("@ss.hasPermi('collects:collects:add')")
    @Log(title = "用户收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserCollects userCollects) {
        return toAjax(userCollectsService.insertUserCollects(userCollects));
    }

    /**
     * 修改用户收藏
     */
    @ApiOperation(value = "修改用户收藏")

    @PreAuthorize("@ss.hasPermi('collects:collects:edit')")
    @Log(title = "用户收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserCollects userCollects) {
        return toAjax(userCollectsService.updateUserCollects(userCollects));
    }

    /**
     * 删除用户收藏
     */
    @ApiOperation(value = "删除用户收藏")
    @PreAuthorize("@ss.hasPermi('collects:collects:remove')")
    @Log(title = "用户收藏", businessType = BusinessType.DELETE)
    @DeleteMapping("/{colIds}")
    public AjaxResult remove(@PathVariable Long[] colIds) {
        return toAjax(userCollectsService.deleteUserCollectsByColIds(colIds));
    }
}
