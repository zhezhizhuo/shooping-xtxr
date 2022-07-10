package com.qgj.web.controller.cart;

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
import com.qgj.common.core.domain.entity.OrderCart;
import com.qgj.order.service.IOrderCartService;
import com.qgj.common.utils.poi.ExcelUtil;
import com.qgj.common.core.page.TableDataInfo;

/**
 * 用户购物车Controller
 *
 * @author qgj
 * @date 2022-04-23
 */
@RestController

@Api(tags = "用户购物车 接口")
@RequestMapping("/order/cart")
public class OrderCartController extends BaseController {
    @Resource
    private IOrderCartService orderCartService;

    /**
     * 查询用户购物车列表
     */
    @ApiOperation(value = "查询用户购物车列表")
    @PreAuthorize("@ss.hasPermi('order:cart:list')")
   
    @GetMapping("/list")
    public TableDataInfo list(OrderCart orderCart) {
        startPage();
        List<OrderCart> list = orderCartService.selectOrderCartList(orderCart);
        return getDataTable(list);
    }

    /**
     * 导出用户购物车列表
     */
    @ApiOperation(value = "导出用户购物车列表")

    @PreAuthorize("@ss.hasPermi('order:cart:export')")
    @Log(title = "用户购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderCart orderCart) {
        List<OrderCart> list = orderCartService.selectOrderCartList(orderCart);
        ExcelUtil<OrderCart> util = new ExcelUtil<OrderCart>(OrderCart.class);
        util.exportExcel(response, list, "用户购物车数据");
    }

    /**
     * 获取用户购物车详细信息
     */
    @ApiOperation(value = "获取用户购物车详细信息")
    @PreAuthorize("@ss.hasPermi('order:cart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(orderCartService.selectOrderCartById(id));
    }

    /**
     * 新增用户购物车
     */
    @ApiOperation(value = "新增用户购物车")
    @ApiOperationSupport(includeParameters = {"ud", "createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('order:cart:add')")
    @Log(title = "用户购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderCart orderCart) {
        return toAjax(orderCartService.insertOrderCart(orderCart));
    }

    /**
     * 修改用户购物车
     */
    @ApiOperation(value = "修改用户购物车")
    @ApiOperationSupport(includeParameters = {"createBy", "createTime", "updateTime", "searchValue",})
    @PreAuthorize("@ss.hasPermi('order:cart:edit')")
    @Log(title = "用户购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderCart orderCart) {
        return toAjax(orderCartService.updateOrderCart(orderCart));
    }

    /**
     * 删除用户购物车
     */
    @ApiOperation(value = "删除用户购物车")
    @PreAuthorize("@ss.hasPermi('order:cart:remove')")
    @Log(title = "用户购物车", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(orderCartService.deleteOrderCartByIds(ids));
    }
}
