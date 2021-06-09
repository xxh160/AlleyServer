package com.edu.nju.alley.controller;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "post")
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("返回所有帖子预览；sortId决定排序方式1按时间2按热度，labelId决定标签筛选方式，0全部1随笔2通知3反馈")
    @GetMapping("/all")
    public ResponseVO<List<PostViewVO>> getAllPosts(@RequestParam Integer sort,
                                                    @RequestParam Integer label) {
        return ResponseVO.<List<PostViewVO>>success().add(postService.getAllPostView(sort, label));
    }

    @ApiOperation("返回特定的帖子")
    @GetMapping("/view/{postId}")
    public ResponseVO<PostVO> getSpecificPost(@PathVariable Integer postId) {
        return ResponseVO.<PostVO>success().add(postService.getSpecificPost(postId));
    }

    @ApiOperation("更新帖子")
    @PostMapping("/update/{postId}")
    public ResponseVO<Object> updatePost(@PathVariable Integer postId,
                                         @RequestBody PostDTO postDTO) {
        postService.updatePost(postId, postDTO);
        return ResponseVO.success();
    }

    @ApiOperation("点赞或者取消点赞帖子")
    @PostMapping("/like")
    public ResponseVO<LikeVO> likePost(@RequestParam Integer postId,
                                       @RequestParam Integer userId) {
        return ResponseVO.<LikeVO>success().add(postService.likePost(postId, userId));
    }

    @ApiOperation("评论帖子")
    @PostMapping("/comment")
    public ResponseVO<CommentVO> commentPost(@RequestBody CommentDTO commentDTO) {
        return ResponseVO.<CommentVO>success().add(postService.commentPost(commentDTO));
    }

    @ApiOperation("新建帖子")
    @PostMapping("/create")
    public ResponseVO<NewRecordVO> createPost(@RequestBody PostDTO postDTO) {
        return ResponseVO.<NewRecordVO>success().add(postService.createPost(postDTO));
    }

    @ApiOperation("删除帖子")
    @DeleteMapping("/delete/{postId}")
    public ResponseVO<Object> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseVO.success();
    }

}
