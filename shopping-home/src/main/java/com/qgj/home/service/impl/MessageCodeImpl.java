package com.qgj.home.service.impl;

import com.qgj.common.core.domain.entity.MessageBean;
import com.qgj.common.utils.CheckSumBuilder;
import com.qgj.home.service.MessageCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;


@Component
public class MessageCodeImpl implements MessageCode {
    //发送验证码的请求路径URL
    private static final String
            SERVER_URL = "https://api.netease.im/sms/sendcode.action";
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String
            APP_KEY = "063c27610cbaea5bc548ab36ad498b10";
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET = "171a3010af89";
    //随机数
    private static final String NONCE = "123456";
    //短信模板ID
    private static final String TEMPLATEID = "19496577";

    //验证码长度，范围4～10，默认为4
    private static final String CODELEN = "6";
    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    @Resource
    RestTemplate restTemplate;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public MessageBean sendMail(String phone) {
        HttpHeaders headers = new HttpHeaders();
        log.info("1.开始发送短信");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
        headers.add("AppKey", APP_KEY);
        headers.add("Nonce", NONCE);
        headers.add("CurTime", curTime);
        headers.add("CheckSum", checkSum);
        log.info("2.填入key 和密钥");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("templateid", TEMPLATEID);
        map.add("mobile", phone);
        map.add("codeLen", CODELEN);
        log.info("3.传入电话号码 ");
        log.info("4.发送请求 ");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<MessageBean> response = restTemplate.postForEntity(SERVER_URL, request, MessageBean.class);
        log.info("5.得到请求响应 ");
        MessageBean body = response.getBody();
        return  body;

    }

    @Override
    public MessageBean sendLoginPhone(String phone) {
        HttpHeaders headers = new HttpHeaders();
        log.info("1.开始发送短信");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
        headers.add("AppKey", APP_KEY);
        headers.add("Nonce", NONCE);
        headers.add("CurTime", curTime);
        headers.add("CheckSum", checkSum);
        log.info("2.填入key 和密钥");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("templateid","19501606");
        map.add("mobile", phone);
        map.add("codeLen", CODELEN);
        log.info("3.传入电话号码 ");
        log.info("4.发送请求 ");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<MessageBean> response = restTemplate.postForEntity(SERVER_URL, request, MessageBean.class);
        log.info("5.得到请求响应 ");
        MessageBean body = response.getBody();
        return  body;
    }
}
