package com.edu.nju.alley.controller;

import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@Api(tags = "Post")
@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("读取帖子")
    @GetMapping("/{postId}")
    public ResponseVO getOnePost(@PathVariable Integer postId) {
        return postService.getPost(postId);
    }

    @ApiOperation("获取帖子列表")
    @GetMapping("/list")
    public ResponseVO postList() {
        return ResponseVO.success().msg("test").add(Arrays.asList("a", "b"));
    }

}
