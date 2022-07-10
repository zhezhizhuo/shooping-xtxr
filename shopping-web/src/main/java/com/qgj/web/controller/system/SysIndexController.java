package com.qgj.web.controller.system;

import com.qgj.common.core.domain.AjaxResult;
import com.qgj.home.service.IHomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qgj.common.config.ShoppingConfig;
import com.qgj.common.utils.StringUtils;

import javax.annotation.Resource;

/**
 * 首页
 *
 * @author qgj
 */
@RestController
public class SysIndexController
{
    /** 系统基础配置 */
    @Autowired
    private ShoppingConfig ShoppingConfig;


    @Resource
    private IHomeBannerService service;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index()
    {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", ShoppingConfig.getName(), ShoppingConfig.getVersion());
    }


    /**
     * 访问首页，数据
     */
    @RequestMapping("/index/data")
    public AjaxResult indexData()
    {
        return  AjaxResult.success(service.getIndexHomeData());
    }
}
