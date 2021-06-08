package com.edu.nju.alley.controller;

import com.edu.nju.alley.service.AnchorService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "anchor")
@RestController
@RequestMapping("/api/anchor")
public class AnchorController {

    private final AnchorService anchorService;

    @Autowired
    public AnchorController(AnchorService anchorService) {
        this.anchorService = anchorService;
    }

    @ApiOperation("获取该锚点对应的所有post预览;根据锚点id返回post；返回根据page id分页；sort是排序方式；label是根据标签筛选")
    @GetMapping("/post/{anchorId}")
    public ResponseVO getAllPosts(@PathVariable Integer anchorId,
                                  @RequestParam("pageId") Integer pageId,
                                  @RequestParam("sort") Integer sort,
                                  @RequestParam("label") Integer label) {
        return ResponseVO.success().add(anchorService.getAllPosts(anchorId, pageId, sort, label));
    }

}
