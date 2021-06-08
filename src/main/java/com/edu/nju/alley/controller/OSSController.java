package com.edu.nju.alley.controller;

import com.edu.nju.alley.service.OSSService;
import com.edu.nju.alley.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseVO callback(@RequestParam String filename,
                               @RequestParam String size,
                               @RequestParam String mimeType,
                               @RequestParam String width,
                               @RequestParam String height) {
        return ResponseVO.success().add(ossService.callback(filename, size, mimeType, width, height));
    }

}
