package com.qgj.web.controller.member;

import com.qgj.common.core.domain.AjaxResult;
import com.qgj.system.domain.SysNotice;
import com.qgj.system.service.ISysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(tags = "消息通知")
@RequestMapping("/user")
@RestController
public class UserMessageController {

    @Resource
    private ISysNoticeService  noticeService;

    @RequestMapping("/notice")
    @ApiOperation("消息通知")
    private AjaxResult  getTell(){
        return  AjaxResult.success(noticeService.selectNoticeList(null));
    }

}
