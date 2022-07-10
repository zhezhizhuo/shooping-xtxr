package com.qgj.web.controller.order;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
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
import com.qgj.common.core.domain.entity.MasterOrder;
import com.qgj.order.service.IMasterOrderService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 用户订单表Controller
 *
 * @author qgj
 * @date 2022-04-25
 */
@RestController

@Api(tags = "用户订单表 接口")
@RequestMapping("/shopping/order")
public class MasterOrderController extends BaseController {
    @Resource
    private IMasterOrderService masterOrderService;

    /**
     * 查询用户订单表列表
     */
    @ApiOperation(value = "查询用户订单表列表")
    @PreAuthorize("@ss.hasPermi('order:order:list')")
   
    @GetMapping("/list")
    public TableDataInfo list(MasterOrder masterOrder) {
        startPage();
        List<MasterOrder> list = masterOrderService.selectMasterOrderList(masterOrder);
        return getDataTable(list);
    }

    /**
     * 导出用户订单表列表
     */
    @ApiOperation(value = "导出用户订单表列表")

    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "用户订单表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MasterOrder masterOrder) {
        List<MasterOrder> list = masterOrderService.selectMasterOrderList(masterOrder);
        ExcelUtil<MasterOrder> util = new ExcelUtil<MasterOrder>(MasterOrder.class);
        util.exportExcel(response, list, "用户订单表数据");
    }

    /**
     * 获取用户订单表详细信息
     */
    @ApiOperation(value = "获取用户订单表详细信息")
    @PreAuthorize("@ss.hasPermi('order:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(masterOrderService.selectMasterOrderById(id));
    }

    /**
     * 新增用户订单表
     */
    @ApiOperation(value = "新增用户订单表")
    @ApiOperationSupport(includeParameters = {"ud", "createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "用户订单表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MasterOrder masterOrder) {
        return toAjax(masterOrderService.insertMasterOrder(masterOrder));
    }

    /**
     * 修改用户订单表
     */
    @ApiOperation(value = "修改用户订单表")
    @ApiOperationSupport(includeParameters = {"createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "用户订单表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MasterOrder masterOrder) {
        return toAjax(masterOrderService.updateMasterOrder(masterOrder));
    }

    /**
     * 删除用户订单表
     */
    @ApiOperation(value = "删除用户订单表")
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "用户订单表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(masterOrderService.deleteMasterOrderByIds(ids));
    }
}
