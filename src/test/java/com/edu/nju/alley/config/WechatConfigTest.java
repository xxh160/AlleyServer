package com.edu.nju.alley.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WechatConfigTest {

    private final WechatConfig wechat;

    @Autowired
    public WechatConfigTest(WechatConfig wechat) {
        this.wechat = wechat;
    }

    @Test
    public void beanTest() {
        System.out.println(this.wechat.getAppId());
        System.out.println(this.wechat.getAppSecret());
    }

}
