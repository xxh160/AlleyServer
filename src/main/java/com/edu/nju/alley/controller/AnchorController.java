package com.edu.nju.alley.controller;

import com.edu.nju.alley.service.AnchorService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Anchor")
@RestController
@RequestMapping("/api/anchor")
public class AnchorController {

    private AnchorService anchorService;

    @Autowired
    public AnchorController(AnchorService anchorService) {
        this.anchorService = anchorService;
    }

    public ResponseVO getAllPosts() {
        return null;
    }

}
