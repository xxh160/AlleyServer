package com.edu.nju.alley.controller;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Post")
@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/view/{postId}")
    public ResponseVO getSpecialPost(@PathVariable Integer postId) {
        return postService.getSpecialPost(postId);
    }

    @GetMapping("/update/{postId}")
    public ResponseVO updatePost(@PathVariable Integer postId, @RequestBody PostDTO postDTO) {
        return postService.updatePost(postId, postDTO);
    }

    @GetMapping("/like")
    public ResponseVO likePost(@RequestParam Integer postId, @RequestParam Integer likerId) {
        return postService.likePost(postId, likerId);
    }

    @GetMapping("/comment")
    public ResponseVO commentPost(@RequestBody CommentDTO commentDTO) {
        return postService.commentPost(commentDTO);
    }

    @GetMapping("/create")
    public ResponseVO createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @GetMapping("/delete/{postId}")
    public ResponseVO deletePost(@PathVariable Integer postId) {
        return postService.deletePost(postId);
    }

}
