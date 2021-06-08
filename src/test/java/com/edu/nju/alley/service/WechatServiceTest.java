package com.edu.nju.alley.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.edu.nju.alley.config.WechatConfig;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WechatServiceTest {

    private final WechatService wechatService;

    private final WechatConfig wechat;

    @Autowired
    public WechatServiceTest(WechatService wechatService,
                             WechatConfig wechat) {
        this.wechatService = wechatService;
        this.wechat = wechat;
    }

    @Test
    public void openidTest() {
        String code = "041k8i000rtZML11ft100BcSv23k8i0H";
        JSONObject json = JSONUtil.parseObj(wechatService.getUserOpenId(
                wechat.getAppId(),
                wechat.getAppSecret(),
                code,
                "authorization_code"));
        System.out.println(json.toStringPretty());
        try {
            if (json.getStr("errcode") != null) throw new NoSuchDataException("无效code");
        } catch (NoSuchDataException e) {
            System.out.println("error");
        }
    }

}
