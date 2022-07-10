package com.qgj.framework.web.service;

import com.qgj.common.core.domain.entity.SysUser;
import com.qgj.common.core.domain.model.LoginUser;
import com.qgj.common.enums.UserStatus;
import com.qgj.common.exception.base.BaseException;
import com.qgj.common.utils.StringUtils;
import com.qgj.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsByTelephoneServiceImpl")
public class UserDetailsByTelephoneServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsByTelephoneServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByPhone(phone);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", phone);
            throw new UsernameNotFoundException("登录用户：" + phone + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", phone);
            throw new BaseException("对不起，您的账号：" + phone + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", phone);
            throw new BaseException("对不起，您的账号：" + phone + " 已停用");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
