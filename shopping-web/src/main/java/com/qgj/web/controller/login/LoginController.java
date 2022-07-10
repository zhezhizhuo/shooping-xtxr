package com.qgj.web.controller.login;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.common.constant.Constants;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.entity.MessageBean;
import com.qgj.common.core.domain.entity.SysUser;
import com.qgj.common.core.domain.model.LoginBody;
import com.qgj.common.core.domain.model.PhoneLoginBody;
import com.qgj.common.core.redis.RedisCache;
import com.qgj.common.exception.ServiceException;
import com.qgj.common.utils.StringUtils;
import com.qgj.framework.web.service.SysLoginService;
import com.qgj.home.service.MessageCode;
import com.qgj.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "PC用户登录")
@ApiSort(2)
public class LoginController extends BaseController {

    @Autowired
    private SysLoginService loginService;


    @Autowired
    private MessageCode messageCode;


    @Resource
    private ISysUserService userService;

    @Resource
    private RedisCache redisCache;

    @PostMapping("/pc/login")
    @ApiOperation("用户登录 ")
    @ApiOperationSupport(includeParameters = {"username", "password"}, ignoreParameters = {"uuid", "code"})
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    @PostMapping("/login/code")
    @ApiOperation("PC手机验证码登录 ")
    public AjaxResult loginCode(@RequestBody PhoneLoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        //寻找这个key
        logger.error("uuid ===>{} ",loginBody.getUuid());
        String key = redisCache.getCacheObject(loginBody.getUuid());
        logger.error(" 是否失效 ===>{}",StringUtils.isEmpty(key));
        if (StringUtils.isEmpty(key)) {
            return AjaxResult.error("验证码不存在或已失效");
        }
        //拿到key里面的值 和用户输入的code 是否一致
        if (!StringUtils.equals(key, loginBody.getCode())) {
            return AjaxResult.error("验证码错误");
        }
        //根据手机号码查询用户
        String token = loginService.loginByTelephone(loginBody.getMobile(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    //获取手机验证码登
    @GetMapping("/login/mobile/code")
    @ApiOperation("PC端  获取 手机发送验证码 ")
    public AjaxResult sendLoginCode(@RequestParam @ApiParam("手机号码") String mobile) {
        //查看改手机号码是否存在
        SysUser user = userService.checkUserPhone(mobile);
        if (user == null) {
            return AjaxResult.error("手机号码不存在或错误");
        }
        //生成key
        String key = Constants.MOBILE +"code:"+ mobile;
        //验证码需要时间 让另外线程处理
        new Thread(() -> {
            //发送验证码
            MessageBean messageBean = messageCode.sendLoginPhone(mobile);
            if (messageBean.getCode() == 200) {       //判断是否发送成功
                logger.error("手机的验证码是 ==>  " + messageBean.getObj());
                logger.error("发送手机验证码成功");
            } else {
                //发送失败
                logger.error("发送验证码失败 原因是==>  {}" + messageBean.getObj() + messageBean.getMsg());
                throw new ServiceException("发送验证码失败");
            }
            //存入redis
            redisCache.setCacheObject(key, messageBean.getObj(), 3, TimeUnit.MINUTES);
        }).start();
        Map<String, String> map = new HashMap<>();
        map.put("uuid", key);
        //把key 返回前端
        return AjaxResult.success(map);
    }
}
