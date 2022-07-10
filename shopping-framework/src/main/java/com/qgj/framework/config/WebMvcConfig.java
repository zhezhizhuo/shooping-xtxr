package com.qgj.framework.config;

import com.qgj.common.config.ShoppingConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhiZhou
 * @createDate: 2022/5/25 20:46
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 设置上传文件映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/shopping-img/uploadPath/");
    }
}
