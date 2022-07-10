package com.qgj.home.service;


import com.qgj.common.core.domain.entity.MessageBean;

public interface MessageCode {

    MessageBean sendMail(String phone);

    MessageBean sendLoginPhone(String  phone);

}
