package com.edu.nju.alley.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebResourceConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 为所有的RestController暴露的服务接口url 添加前缀/api
        configurer.addPathPrefix("/api", c -> c.isAnnotationPresent(RestController.class));
    }

}
