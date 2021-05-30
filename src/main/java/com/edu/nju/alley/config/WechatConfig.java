package com.edu.nju.alley.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfig {

    @Bean
    @ConfigurationProperties("wechat")
    public Wechat wechat() {
        return new Wechat();
    }

    @Data
    public static class Wechat {

        @Value("app-id")
        private String appId;

        @Value("app-secret")
        private String appSecret;

    }

}
