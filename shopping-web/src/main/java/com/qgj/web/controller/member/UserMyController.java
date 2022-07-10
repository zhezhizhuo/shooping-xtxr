package com.qgj.web.controller.member;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.address.service.IUserAddressService;
import com.qgj.collects.service.IUserCollectsService;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.Goods;
import com.qgj.common.core.domain.entity.UserAddress;
import com.qgj.common.core.page.TableDataInfo;
import com.qgj.common.utils.DateUtils;
import com.qgj.foot.service.IUserFootService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

@RestController
@Api(tags = "MEMBER 所有模块 (足迹,收藏,地址)")
@ApiSort(5)
@RequestMapping("/member")
public class UserMyController extends BaseController {

    @Resource
    private IUserFootService footService;

    @Resource
    private IUserCollectsService collectsService;
    @Resource
    private IUserAddressService userAddressService;
        //获取我的足迹
    @GetMapping("/browseHistory")
    @ApiOperation(value = "获取我的足迹 分页查询")
    public TableDataInfo getBrowseHistory(){
          startPage();
             return  getDataTable(footService.selectUserFootListByUid(getUserId()));
    }
    //删除足迹二

    @GetMapping("/browseHistory/batch")
    @ApiOperation(value = "删除我的足迹")
    public AjaxResult remove(@RequestParam @ApiParam("足迹id") Long id) {
        return toAjax(footService.deleteUserFootByFtIds(id));
    }

    //收藏的是商品
    @GetMapping("/collect/add/{id}")
    @ApiOperation(value = "添加收藏",tags = "用户收藏")
    public AjaxResult getUserAddCollect( @PathVariable("id") Long id){
        //判断是否添加该商品
        List<Goods> goods = collectsService.selectUserCollectListByUid(getUserId(), String.valueOf(id));
        if (goods!=null&&goods.size()!=0){
            return new AjaxResult(400,"已添加该商品到收藏");
        }
        return  AjaxResult.success(collectsService.getUserAddCollect(getUserId(),id));
    }

    //查看该商品我是否收藏
    @GetMapping("/collect/isCollect")
    @ApiOperation(value = "用户是否收藏该商品",tags = "用户收藏")
    public AjaxResult getUserIsCollect(@RequestParam @ApiParam("商品id") Long id){
        //判断是否添加该商品
        AjaxResult result = AjaxResult.success();
        List<Goods> goods = collectsService.selectUserCollectListByUid(getUserId(), String.valueOf(id));
        if (goods!=null&&goods.size()!=0){
            result.put("isCollect",true); //已收藏
        }
        result.put("isCollect",false);//未收藏

        return  result;
    }
    //获取收藏列表
    @GetMapping("/collect")
    @ApiOperation(value = "用户收藏列表 分页查询",tags = "用户收藏")
    public TableDataInfo getUserCollects(){
        startPage();
        return  getDataTable(collectsService.selectUserCollectListByUid(getUserId()));
    }

    @GetMapping("/collect/batch")
    @ApiOperation(value = "取消收藏 ",tags = "用户收藏")
    public AjaxResult getUserDelCollect( @ApiParam(value = "id",required = true) Long id){
        return  AjaxResult.success(collectsService.getUserDelCollect(getUserId(),id));
    }

    @GetMapping("/collect/batch/pid")
    @ApiOperation(value = "根据商品id取消收藏 ",tags = "用户收藏")
    public AjaxResult getUserDelCollectByPid( @ApiParam(value = "id",required = true) Long id){
        return  AjaxResult.success(collectsService.getUserDelCollectByPid(getUserId(),id));
    }
    //添加收获地址
    @PostMapping("/userAdd/address")
    @ApiOperation(value = "添加收获地址",tags = "用户地址")
    public AjaxResult getUserAddAddress(@RequestBody  UserAddress userAddress){
        userAddress.setUid(getUserId());
        Date now = DateUtils.getNowDate();
        userAddress.setCreateTime(now);
        userAddress.setUpdateTime(now);
        return  AjaxResult.success(userAddressService.insertUserAddress(userAddress));
    }
    //删除收获地址
    @GetMapping("/address/del")
    @ApiOperation(value = "删除收获地址",tags = "用户地址")
    public AjaxResult getUserDelAddress(    @ApiParam("地址id") Long id){
        return  AjaxResult.success(userAddressService.deleteUserAddressById(id));
    }

    //查询收获地址
    @GetMapping("/address")
    @ApiOperation(value = "获取收获地址",tags = "用户地址")
    public AjaxResult getUserSelectAddress(){
        return  AjaxResult.success(userAddressService.selectUserADdresByUser(getUserId()));
    }

    //修改收获地址
    @PostMapping("/address/update")
    @ApiOperation(value = "修改收获地址",tags = "用户地址")
    public AjaxResult getUserUpadteAddress(@RequestBody UserAddress userAddress){
        return  AjaxResult.success(userAddressService.updateUserAddress(userAddress));
    }

    //设为默认值地址
    @GetMapping("/address/setDefault")
    @ApiOperation(value = "设为默认值地址",tags = "用户地址")
    public AjaxResult getUserDefalutAddress(@RequestParam   @ApiParam("地址id") String id){

        return  AjaxResult.success(userAddressService.userSetAddressDefault(id));
    }
}
