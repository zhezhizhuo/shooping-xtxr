package com.qgj.web.controller.foot;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.qgj.common.core.domain.entity.UserFoot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.qgj.foot.service.IUserFootService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 用户足迹Controller
 *
 * @author qgj
 * @date 2022-04-19
 */
@RestController

@Api(tags = "用户足迹 接口")
@RequestMapping("/user/foot")
public class UserFootController extends BaseController {
    @Resource
    private IUserFootService userFootService;

    /**
     * 查询用户足迹列表
     */
    @ApiOperation(value = "查询用户足迹列表")
    @PreAuthorize("@ss.hasPermi('foot:foot:list')")
   
    @GetMapping("/list")
    public TableDataInfo list(UserFoot userFoot) {
        startPage();
        List<UserFoot> list = userFootService.selectUserFootList(userFoot);
        return getDataTable(list);
    }

    /**
     * 导出用户足迹列表
     */
    @ApiOperation(value = "导出用户足迹列表")

    @PreAuthorize("@ss.hasPermi('foot:foot:export')")
    @Log(title = "用户足迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserFoot userFoot) {
        List<UserFoot> list = userFootService.selectUserFootList(userFoot);
        ExcelUtil<UserFoot> util = new ExcelUtil<UserFoot>(UserFoot.class);
        util.exportExcel(response, list, "用户足迹数据");
    }

    /**
     * 获取用户足迹详细信息
     */
    @ApiOperation(value = "获取用户足迹详细信息")
    @PreAuthorize("@ss.hasPermi('foot:foot:query')")
    @GetMapping(value = "/{ftId}")
    public AjaxResult getInfo(@PathVariable("ftId") Long ftId) {
        return AjaxResult.success(userFootService.selectUserFootByFtId(ftId));
    }

    /**
     * 新增用户足迹
     */
    @ApiOperation(value = "新增用户足迹")
    @ApiOperationSupport(includeParameters = {"ud", "createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('foot:foot:add')")
    @Log(title = "用户足迹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserFoot userFoot) {
        return toAjax(userFootService.insertUserFoot(userFoot));
    }

    /**
     * 修改用户足迹
     */
    @ApiOperation(value = "修改用户足迹")
    @ApiOperationSupport(includeParameters = {"createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('foot:foot:edit')")
    @Log(title = "用户足迹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserFoot userFoot) {
        return toAjax(userFootService.updateUserFoot(userFoot));
    }

    /**
     * 删除用户足迹
     */
    @ApiOperation(value = "删除用户足迹")
    @PreAuthorize("@ss.hasPermi('foot:foot:remove')")
    @Log(title = "用户足迹", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ftIds}")
    public AjaxResult remove(@PathVariable Long[] ftIds) {
        return toAjax(userFootService.deleteUserFootByFtIds(ftIds));
    }
}
