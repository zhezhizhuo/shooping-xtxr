package com.qgj.home.service;

import com.qgj.common.core.domain.entity.SysUser;
import org.springframework.stereotype.Service;

public interface MailService {
    void sendMail(String url, SysUser user,String emil);
//    void sendMail(SysUser user, String code);
}
