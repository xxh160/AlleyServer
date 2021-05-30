package com.edu.nju.alley.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {

    @Bean
    @ConfigurationProperties("oss")
    public OSSInfo oss() {
        return new OSSInfo();
    }

    @Data
    public static class OSSInfo {

        @Value("bucket-name")
        private String bucketName;

        @Value("end-point")
        private String endPoint;

        @Value("accesskey-id")
        private String accesskeyId;

        @Value("accesskey-secret")
        private String accesskeySecret;

        @Value("dir")
        private String dir;

        @Value("callback")
        private String callback;

    }

}
