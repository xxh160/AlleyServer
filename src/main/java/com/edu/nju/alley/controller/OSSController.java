package com.edu.nju.alley.controller;

import com.edu.nju.alley.service.OSSService;
import com.edu.nju.alley.vo.OSSCallbackResultVO;
import com.edu.nju.alley.vo.OSSPolicyVO;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "oss")
@RestController
@RequestMapping("/api/oss")
public class OSSController {

    private final OSSService ossService;

    @Autowired
    public OSSController(OSSService ossService) {
        this.ossService = ossService;
    }

    @ApiOperation("")
    @GetMapping("/policy")
    public ResponseVO<OSSPolicyVO> policy() {
        return ResponseVO.<OSSPolicyVO>success().add(ossService.policy());
    }

    @ApiOperation("")
    @PostMapping("/callback")
    public ResponseVO<OSSCallbackResultVO> callback(@RequestParam String filename,
                                                    @RequestParam String size,
                                                    @RequestParam String mimeType,
                                                    @RequestParam String width,
                                                    @RequestParam String height) {
        return ResponseVO.<OSSCallbackResultVO>success().add(ossService.callback(filename, size, mimeType, width, height));
    }

}
