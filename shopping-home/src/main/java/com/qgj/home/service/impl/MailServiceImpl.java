package com.qgj.home.service.impl;


import com.qgj.common.core.domain.entity.SysUser;
import com.qgj.common.utils.DateUtils;
import com.qgj.home.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String mai;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    @Override
    public void sendMail(String url, SysUser user,String emil) {
        //创建邮箱对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
            //设置邮箱主题
            message.setSubject("欢迎使用您!! 小兔鲜儿的商城 ");
            //设置发件人
            message.setFrom(mai);
            //设置收件人
            message.setTo(emil);
            //发送日期
            message.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("code",url);
            context.setVariable("name",user.getUserName());
            context.setVariable("email",user.getEmail());
            context.setVariable("activeTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS,new Date()));
            String text = templateEngine.process("active-account.html",context);
            //发送邮件
            message.setText(text,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

//    @Override
//    public void sendMail(SysUser user, String code) {
//        //创建邮箱对象
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        try {
//            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
//            //设置邮箱主题
//            message.setSubject("欢迎使用您!! 修改密码的邮件来了  ");
//            //设置发件人
//            message.setFrom(mai);
//            //设置收件人
//            message.setTo(user.getEmail());
//            //发送日期
//            message.setSentDate(new Date());
//            Context context = new Context();
//            context.setVariable("code",code);
//            context.setVariable("name",user.getUserName());
//            String text = templateEngine.process("verification-code.html",context);
//
//            //发送邮件
//            message.setText(text,true);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        javaMailSender.send(mimeMessage);
//    }


}
