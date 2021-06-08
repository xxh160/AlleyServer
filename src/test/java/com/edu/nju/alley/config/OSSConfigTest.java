package com.edu.nju.alley.config;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OSSConfigTest {

    private final OSSConfig ossConfig;

    private final OSSClient ossClient;

    @Autowired
    public OSSConfigTest(OSSConfig ossConfig,
                         OSSClient ossClient) {
        this.ossConfig = ossConfig;
        this.ossClient = ossClient;
    }

    @Test
    public void beanTest() {
        System.out.println(ossConfig.getAccesskeyId());
        System.out.println(ossClient.getConnectionPoolStats());
    }

}
