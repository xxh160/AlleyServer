package com.edu.nju.alley.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wechat", url = "https://api.weixin.qq.com/")
public interface WechatService {
    
    @GetMapping("/sns/jscode2session")
    Object getUserOpenId(@RequestParam String appid,
                         @RequestParam String secret,
                         @RequestParam String js_code,
                         @RequestParam String grant_type);
}
