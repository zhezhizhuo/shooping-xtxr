package com.qgj.web.controller.register;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.qgj.common.constant.Constants;
import com.qgj.common.core.controller.BaseController;
import com.qgj.common.core.domain.AjaxResult;
import com.qgj.common.core.domain.entity.MessageBean;
import com.qgj.common.core.domain.entity.SysUser;
import com.qgj.common.core.domain.model.LoginBody;
import com.qgj.common.core.domain.model.RegisterModel;
import com.qgj.common.core.redis.RedisCache;
import com.qgj.common.exception.ServiceException;
import com.qgj.common.utils.MessageUtils;
import com.qgj.common.utils.SecurityUtils;
import com.qgj.common.utils.StringUtils;
import com.qgj.framework.manager.AsyncManager;
import com.qgj.framework.manager.factory.AsyncFactory;
import com.qgj.home.service.MessageCode;
import com.qgj.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.util.MapUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "注册模块")
@ApiSort(3)
@RequestMapping("/register")
public class UserRegisterController extends BaseController {


    @Resource
    private ISysUserService sysUserService;

    @Resource
    private MessageCode messageCode;

    @Resource
    private RedisCache redisCache;

    @PostMapping
    @ApiOperation("用户注册")
    public AjaxResult UserRegister(@RequestBody(required = true)  RegisterModel user) {

        /**
         *  验证手机验证码
         */
        //寻找这个key
        String key = redisCache.getCacheObject(user.getUuid());
        if (StringUtils.isEmpty(key)){
            return  AjaxResult.error("验证码不存在或已失效");
        }
        //拿到key里面的值 和用户输入的code 是否一致
        if (!StringUtils.equals(key,user.getCode())){
            return  AjaxResult.error("验证码错误");
        }
        /**
         * 开始注册
         */

        SysUser sysUser = new SysUser();
        sysUser.setUserName(user.getAccount());
        sysUser.setNickName(user.getNick_name());
        //注册到sys表里面
        sysUser.setSex(String.valueOf(user.getSex()));
        //电话
        sysUser.setPhonenumber(user.getMobile());
        sysUser.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        //设置默认头像
        sysUser.setAvatar("http://raay2lp2t.hn-bkt.clouddn.com/avatar/2022/04/14/blob_20220414214415A002.jpeg");
        boolean regFlag = sysUserService.registerUser(sysUser);
        String msg ="注册成功";
        if (!regFlag)
        {
            msg = "注册失败,请联系系统管理人员";
        }
        else
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(sysUser.getUserName(), Constants.REGISTER,
                    MessageUtils.message("user.register.success")));
        }
        AjaxResult result = AjaxResult.success(msg);

        return result;
    }

    /**
     *  验证手机验证码
     */

    //根据电话还没发送验证码
    @GetMapping("/code")
    @ApiOperation(value = "注册-验证手机验证码", notes = "返回uuid  唯一表示 每一个验证码都不一样")
    public AjaxResult sendMobilCode(@RequestParam String mobile) {
        //生成key
        String key = Constants.MOBILE + mobile;

//        发送验证码需要时间 让另外线程处理
        new Thread(() -> {
            //发送验证码
            MessageBean messageBean = messageCode.sendMail(mobile);
            if (messageBean.getCode() == 200) {       //判断是否发送成功
                //2.  存储 在 session 里面

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
//                    redisCache.setCacheObject(key, "123123", 3, TimeUnit.MINUTES);

        Map<String, String> map = new HashMap<>();
        map.put("uuid", key);
        //把key 返回前端
        return AjaxResult.success(map);
    }


    //校验用户名
    @GetMapping("/check")
    @ApiOperation("注册-校验用户名唯一")
    public AjaxResult UserCheckName(@RequestParam String account) {
        String isCheck = sysUserService.checkUserNameUnique(account);
        if (isCheck == "1") {
            return AjaxResult.success(true);
        }
        return AjaxResult.success(false);
    }

    //校验电话唯一
    @GetMapping("/check/mobile")
    @ApiOperation("注册-校验电话唯一")
    public AjaxResult UserCheckMobile(@RequestParam String mobile) {
        SysUser sysUser = sysUserService.checkUserPhone(mobile);
        if (sysUser == null) {
            return AjaxResult.success(false);
        }
        return AjaxResult.success(true);
    }





}
