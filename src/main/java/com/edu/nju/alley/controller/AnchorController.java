package com.edu.nju.alley.controller;

import com.edu.nju.alley.service.AnchorService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "anchor")
@RestController
@RequestMapping("/anchor")
public class AnchorController {

    private AnchorService anchorService;

    @Autowired
    public AnchorController(AnchorService anchorService) {
        this.anchorService = anchorService;
    }

    @GetMapping("/post/{anchorId}")
    public ResponseVO getAllPosts(@PathVariable Integer anchorId,
                                  @RequestParam("pageId") Integer pageId,
                                  @RequestParam("sort") Integer sort,
                                  @RequestParam("label") Integer label) {
        return null;
    }

}
