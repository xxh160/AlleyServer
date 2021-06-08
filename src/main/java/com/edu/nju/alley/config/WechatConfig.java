package com.edu.nju.alley.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("wechat")
public class WechatConfig {

    @Value("app-id")
    private String appId;

    @Value("app-secret")
    private String appSecret;

}
