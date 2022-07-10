package com.qgj.web.controller.userorder;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.OrderUserEntity;
import com.qgj.common.core.domain.entity.MasterOrder;
import com.qgj.common.core.page.TableDataInfo;
import com.qgj.order.service.IMasterOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户订单模块")
@ApiSort(6)
@RestController()
@RequestMapping("/user")
public class UserOrderController extends BaseController {


    @Resource
    private IMasterOrderService orderService;


    //提交订单
    @PostMapping("/order/commit")
    @ApiOperation(value = "提交订单")
    public AjaxResult order(@RequestBody OrderUserEntity order) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("orderSn",orderService.userUpOrderByUser(order));
        return ajax;
    }
    //获取我的订单列表
    @GetMapping("/order/list")
    @ApiOperation(value = "获取我的订单列表")
    public AjaxResult querList(  @ApiParam("默认不传查询全部，0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->已取消") String status) {
        return AjaxResult.success("操作成功",orderService.selectUserOrder(getLoginUser().getUsername(), status));
    }
    //删除订单
    @GetMapping("/order/del/{id}")
    @ApiOperation(value = "删除订单")
    public AjaxResult del(@PathVariable("id") @ApiParam("订单 ID") Long id) {
        MasterOrder masterOrder = new MasterOrder();
        masterOrder.setId(id);
        masterOrder.setDeleteStatus(0);

        return toAjax(orderService.updateMasterOrder(masterOrder));
    }
    //确认收获
    @GetMapping("/order/sure/{id}")
    @ApiOperation(value = "确认收获")
    public AjaxResult sure(@PathVariable("id") @ApiParam("订单 ID") String id) {
        MasterOrder masterOrder = new MasterOrder();
        masterOrder.setOrderSn(id);
        masterOrder.setStatus(3);
        return toAjax(orderService.updateMasterOrder(masterOrder));
    }

    //取消订单
    @GetMapping("/order/down/{id}")
    @ApiOperation(value = "取消订单")
    public AjaxResult calne(@PathVariable("id") @ApiParam("订单 ID") String id) {
        MasterOrder masterOrder = new MasterOrder();
        masterOrder.setOrderSn(id);
        masterOrder.setDeleteStatus(0);
        return toAjax(orderService.UserDelOrderMaster(masterOrder));
    }


}
