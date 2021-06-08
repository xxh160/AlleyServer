package com.edu.nju.alley.config;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("oss")
public class OSSConfig {

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(endPoint, accesskeyId, accesskeySecret);
    }

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

    @Value("max_size")
    private String maxSize;

}
