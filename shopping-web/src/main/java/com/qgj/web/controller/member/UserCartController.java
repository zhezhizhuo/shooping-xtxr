package com.qgj.web.controller.member;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.UserCartEntity;
import com.qgj.common.core.domain.model.UserCard;
import com.qgj.order.service.IOrderCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member/cart")
@Api(tags = "用户购物车模块")
@ApiSort(4)
public class UserCartController  extends BaseController {

     @Resource
        private IOrderCartService orderCartService;

    @GetMapping
    @ApiOperation("获取购物车列表")
    public AjaxResult  getUserCartList(){
      List<UserCartEntity> cars =  this.orderCartService.selectCarByUserId(getUserId());
        return AjaxResult.success(cars);
    }


    @DeleteMapping("/cart/{ids}")
    @ApiOperation("删除/清空购物车商品")
    public AjaxResult  getUserDeleteCart(@PathVariable Long[] ids){
            return toAjax(orderCartService.deleteOrderCartByIds(ids));
    }


      //加入购物车
      @PostMapping()
      @ApiOperation("加入购物车")
      public AjaxResult  getUserCartAdd(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody UserCard card){
          int isSuccess = orderCartService.UserAddCart( card);
          if (isSuccess == -1){
              return  AjaxResult.error("商品不存在");
          }
          return toAjax(isSuccess);
      }


    //合并购物车
    @PostMapping("merge")
    @ApiOperation("合并购物车")
    public AjaxResult  getUserCartMerge(@RequestBody List<UserCard> userCards){
        int isSuccess = orderCartService.UserAddCartList( userCards);
        if (isSuccess == -1){
            return  AjaxResult.error("商品不存在");
        }
        return toAjax(isSuccess);
    }

     @GetMapping("/count")
     @ApiOperation("获取购物车数量")
     public AjaxResult  getUserCartCount(){
         Map<String, Integer> count = new HashMap<>();
         count.put("count",orderCartService.selectUserCartCount(getUserId()));
         AjaxResult result =AjaxResult.success(count);
         return result;
     }
}
