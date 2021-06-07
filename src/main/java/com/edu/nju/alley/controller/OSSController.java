package com.edu.nju.alley.controller;

import com.edu.nju.alley.service.OSSService;
import com.edu.nju.alley.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/oss")
public class OSSController {

    private final OSSService ossService;

    @Autowired
    public OSSController(OSSService ossService) {
        this.ossService = ossService;
    }

    @GetMapping("/policy")
    public ResponseVO policy() {
        return ResponseVO.success().add(ossService.policy());
    }

    @PostMapping("/callback")
    public ResponseVO callback(HttpServletRequest request) {
        return ResponseVO.success().add(ossService.callback(request));
    }

}
