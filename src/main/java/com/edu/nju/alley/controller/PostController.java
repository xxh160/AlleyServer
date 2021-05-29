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

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public ResponseVO getAllPosts(@RequestParam Integer sort,
                                  @RequestParam Integer label) {
        return ResponseVO.success().add(postService.getAllPostView(sort, label));
    }

    @GetMapping("/view/{postId}")
    public ResponseVO getSpecialPost(@PathVariable Integer postId) {
        return ResponseVO.success().add(postService.getSpecificPost(postId));
    }

    @PostMapping("/update/{postId}")
    public ResponseVO updatePost(@PathVariable Integer postId,
                                 @RequestBody PostDTO postDTO) {
        postService.updatePost(postId, postDTO);
        return ResponseVO.success();
    }

    @PostMapping("/like")
    public ResponseVO likePost(@RequestParam Integer postId,
                               @RequestParam Integer likerId) {
        return ResponseVO.success().add(postService.likePost(postId, likerId));
    }

    @PostMapping("/comment")
    public ResponseVO commentPost(@RequestBody CommentDTO commentDTO) {
        return ResponseVO.success().add(postService.commentPost(commentDTO));
    }

    @PostMapping("/create")
    public ResponseVO createPost(@RequestBody PostDTO postDTO) {
        return ResponseVO.success().add(postService.createPost(postDTO));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseVO deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseVO.success();
    }

}
