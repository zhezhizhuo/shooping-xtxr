package com.qgj.web.controller.home;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.GoodsEntity;
import com.qgj.common.core.domain.entity.UserFoot;
import com.qgj.common.core.page.TableDataInfo;
import com.qgj.common.utils.DateUtils;
import com.qgj.common.utils.SecurityUtils;
import com.qgj.common.utils.StringUtils;
import com.qgj.foot.service.IUserFootService;
import com.qgj.product.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "商品详情模块")
@ApiSort(3)
public class ProductInfoController extends BaseController {

    @Resource
    private IProductService productService;

    @Resource
    private IUserFootService footService;


    //商品详情
    @GetMapping("/goods")
    @ApiOperation(value = "商品详情", notes = "作者: Zhizhuo ")
    public AjaxResult goods(@RequestParam(required = true) Long id, HttpServletRequest request) {
        GoodsEntity goods = productService.queryProductInfoById(id);
        /**
         * 这个方法  看是否 能拿到token
         * 如果拿到token 了  说明   我们要把足迹添加到足迹表里面
         * 然后在放回  商品详情的数据  ok
         */
        try {
                //添加足迹
                Long userId = SecurityUtils.getUserId();
            logger.error(" 添加足迹  UserId === >{}",userId);

            UserFoot userFoot = new UserFoot();
                userFoot.setPid(id);
                userFoot.setUserId(userId);
                userFoot.setCreateTime(DateUtils.getNowDate());
                footService.insertUserFoot(userFoot);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  AjaxResult.success(goods);
    }

    //同类商品  同类推荐(也支持猜你喜欢)
    @GetMapping("/goods/relevant")
    @ApiOperation(value = "同类商品   同类推荐(也支持猜你喜欢)", notes = "作者: Zhizhuo ")
    public AjaxResult goodRelevant(@RequestParam(required = true) Long id,
                                   @RequestParam(defaultValue = "16", required = true, name = "limit") Integer limit) {
        return AjaxResult.success(productService.queryRelevantInfoById(id, limit));
    }

    //商品评论信息
    @GetMapping("/goods/{id}/evaluate/page")
    @ApiOperation(value = "商品评论信息", notes = "作者: Zhizhuo ")
    public TableDataInfo goodComments(@PathVariable("id") @ApiParam("商品id") Long id) {
        startPage();
        return getDataTable(productService.selectProductCommentByPid(id));
    }

    @GetMapping("/goods/{id}/evaluate/page/hot")
    @ApiOperation(value = "商品评论信息", notes = "作者: Zhizhuo ")
    public TableDataInfo goodCommentsHot(@PathVariable("id") @ApiParam("商品id") Long id) {
        startPage();
        return getDataTable(productService.selectProductCommentByPidHot(id));
    }
}
