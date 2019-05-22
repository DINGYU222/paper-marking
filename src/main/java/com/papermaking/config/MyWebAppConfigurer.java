package com.papermaking.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义资源请求 过来的/assets/** 被分发到WEB-INF/static/下
 */
@Configurable
@Component
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/question/assets/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }

}
