package com.qgj.paly.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "zfb")
@Component
public class AlipayConfig {
    @Override
    public String toString() {
        return "AlipayConfig{" +
                "APP_ID='" + APP_ID + '\'' +
                ", APP_PRIVATE_KEY='" + APP_PRIVATE_KEY + '\'' +
                ", ALIPAY_PUBLIC='" + ALIPAY_PUBLIC_KEY + '\'' +
                ", NOTIFY_URL='" + NOTIFY_URL + '\'' +
                ", RETURN_URL='" + RETURN_URL + '\'' +
                ", KEY_TYPE='" + KEY_TYPE + '\'' +
                ", CHARSET='" + CHARSET + '\'' +
                ", GETWAYURL='" + GETWAYURL + '\'' +
                '}';
    }

    private String APP_ID;
    private String APP_PRIVATE_KEY;
    private String ALIPAY_PUBLIC_KEY;
    private String NOTIFY_URL;
    private String RETURN_URL;
    private String KEY_TYPE;
    private String CHARSET;
    private String GETWAYURL;

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getAPP_PRIVATE_KEY() {
        return APP_PRIVATE_KEY;
    }

    public void setAPP_PRIVATE_KEY(String APP_PRIVATE_KEY) {
        this.APP_PRIVATE_KEY = APP_PRIVATE_KEY;
    }

    public String getALIPAY_PUBLIC_KEY() {
        return ALIPAY_PUBLIC_KEY;
    }

    public void setALIPAY_PUBLIC_KEY(String ALIPAY_PUBLIC_KEY) {
        this.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
    }

    public String getNOTIFY_URL() {
        return NOTIFY_URL;
    }

    public void setNOTIFY_URL(String NOTIFY_URL) {
        this.NOTIFY_URL = NOTIFY_URL;
    }

    public String getRETURN_URL() {
        return RETURN_URL;
    }

    public void setRETURN_URL(String RETURN_URL) {
        this.RETURN_URL = RETURN_URL;
    }

    public String getKEY_TYPE() {
        return KEY_TYPE;
    }

    public void setKEY_TYPE(String KEY_TYPE) {
        this.KEY_TYPE = KEY_TYPE;
    }

    public String getCHARSET() {
        return CHARSET;
    }

    public void setCHARSET(String CHARSET) {
        this.CHARSET = CHARSET;
    }

    public String getGETWAYURL() {
        return GETWAYURL;
    }

    public void setGETWAYURL(String GETWAYURL) {
        this.GETWAYURL = GETWAYURL;
    }
}
