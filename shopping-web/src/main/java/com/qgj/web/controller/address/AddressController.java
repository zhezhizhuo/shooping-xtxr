package com.qgj.web.controller.address;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.qgj.address.service.IUserAddressService;
import com.qgj.common.core.domain.entity.UserAddress;
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

import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 地址管理Controller
 *
 * @author qgj
 * @date 2022-04-18
 */
@RestController
@Api(tags = "地址管理 接口")
@RequestMapping("/user/address")
public class AddressController extends BaseController {
    @Autowired
    private IUserAddressService userAddressService;

    /**
     * 查询地址管理列表
     */
    @ApiOperation(value = "查询地址管理列表")
    @PreAuthorize("@ss.hasPermi('home:address:list')")

    @GetMapping("/list")
    public TableDataInfo list(UserAddress userAddress) {
        startPage();
        List<UserAddress> list = userAddressService.selectUserAddressList(userAddress);
        return getDataTable(list);
    }

    /**
     * 导出地址管理列表
     */
    @ApiOperation(value = "导出地址管理列表")

    @PreAuthorize("@ss.hasPermi('home:address:export')")
    @Log(title = "地址管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserAddress userAddress) {
        List<UserAddress> list = userAddressService.selectUserAddressList(userAddress);
        ExcelUtil<UserAddress> util = new ExcelUtil<UserAddress>(UserAddress.class);
        util.exportExcel(response, list, "地址管理数据");
    }

    /**
     * 获取地址管理详细信息
     */
    @ApiOperation(value = "获取地址管理详细信息")
    @PreAuthorize("@ss.hasPermi('home:address:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(userAddressService.selectUserAddressById(id));
    }

    /**
     * 新增地址管理
     */
    @ApiOperation(value = "新增地址管理")
    @PreAuthorize("@ss.hasPermi('home:address:add')")
    @Log(title = "地址管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAddress userAddress) {
        return toAjax(userAddressService.insertUserAddress(userAddress));
    }

    /**
     * 修改地址管理
     */
    @ApiOperation(value = "修改地址管理")
    @PreAuthorize("@ss.hasPermi('home:address:edit')")
    @Log(title = "地址管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAddress userAddress) {
        return toAjax(userAddressService.updateUserAddress(userAddress));
    }

    /**
     * 删除地址管理
     */
    @ApiOperation(value = "删除地址管理")
    @PreAuthorize("@ss.hasPermi('home:address:remove')")
    @Log(title = "地址管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(userAddressService.deleteUserAddressByIds(ids));
    }
}
