package com.qgj.web.controller.system;

import java.io.IOException;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.common.utils.file.FileUpLoad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.qgj.common.annotation.Log;
import com.qgj.common.config.ShoppingConfig;
import com.qgj.common.constant.UserConstants;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.entity.SysUser;
import com.qgj.common.core.domain.model.LoginUser;
import com.qgj.common.enums.BusinessType;
import com.qgj.common.utils.SecurityUtils;
import com.qgj.common.utils.StringUtils;
import com.qgj.common.utils.file.FileUploadUtils;
import com.qgj.framework.web.service.TokenService;
import com.qgj.system.service.ISysUserService;

import static com.qgj.common.utils.file.FileUploadUtils.extractFilename;

/**
 * 个人信息 业务处理
 *
 * @author qgj
 */
@RestController
@RequestMapping("/system/user/profile")
@Api(tags = "个人信息")
@ApiSort(6)
public class SysProfileController extends BaseController
{
    @Autowired
    private ISysUserService userService;
    @Autowired
    private FileUpLoad fileUpLoad;


    @Autowired
    private TokenService tokenService;

    /**
     * 个人信息
     */
    @GetMapping
    @ApiOperation(value = "获取个人信息")
    public AjaxResult profile()
    {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改用户")
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user)
    {
        System.out.println(user.toString());
        LoginUser loginUser = getLoginUser();
        SysUser sysUser = loginUser.getUser();
        user.setUserName(sysUser.getUserName());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUserId(sysUser.getUserId());
        user.setPassword(null);
        if (userService.updateUserProfile(user) > 0)
        {
            // 更新缓存用户信息
            sysUser.setNickName(user.getNickName());
            sysUser.setPhonenumber(user.getPhonenumber());
            sysUser.setEmail(user.getEmail());
            sysUser.setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    @ApiOperation(value = "重置密码")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
        LoginUser loginUser = getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    @ApiOperation(value = "头像上传")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            LoginUser loginUser = getLoginUser();
//            String avatar = fileUpLoad.uploadFile(file.getInputStream(),"avatar/"+extractFilename(file));
            logger.info(ShoppingConfig.getAvatarPath());
            String avatar = FileUploadUtils.upload(ShoppingConfig.getAvatarPath(), file);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar))
            {
                logger.info("头像地址==> {}",avatar);
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
}
