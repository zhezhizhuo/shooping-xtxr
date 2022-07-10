package com.qgj.web.controller.maill;

import com.google.common.net.InetAddresses;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.entity.HomeBanner;
import com.qgj.common.core.domain.entity.SysUser;
import com.qgj.common.utils.ip.IpUtils;
import com.qgj.home.service.IHomeBannerService;
import com.qgj.home.service.MailService;
import com.qgj.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TimerTask;

@RestController
@RequestMapping("/mail")
@Api(tags = "邮箱操作")
public class UserMaillController extends BaseController {

    @Resource
    private IHomeBannerService homeBannerService;

    @Resource
    private ISysUserService  userService;
    //发送验证码
        @PostMapping("/verifyMail")
        @ApiOperation("发送邮箱请携带token 以及邮箱号")
        public AjaxResult sendEail(HttpServletRequest request, @RequestParam String email){
            SysUser sysUser = userService.selectUserById(getUserId());
            String ip = IpUtils.getHostName();
              int localPort = request.getLocalPort();
            homeBannerService.VerifyUserEmail(ip+localPort,sysUser,email);
            return  toAjax(1);
        }
        //添加邮箱

    @GetMapping("/code/{code}")
    @ApiOperation("验证邮箱")
    public AjaxResult VerifyEmail( @PathVariable("code") String code){
        if (userService.updateUserEmail(code)==-1) {
            return AjaxResult.error("验证码已失效或不存在");
        }else {
            return AjaxResult.success("修改邮箱成功");
        }
    }

}
