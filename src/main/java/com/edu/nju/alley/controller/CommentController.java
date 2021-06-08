package com.edu.nju.alley.controller;


import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "comment")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation("评论一条评论;评论一条评论，更新comment、user_comment_rel和comment_rel数据库")
    @PostMapping("/comment")
    public ResponseVO commentComment(@RequestBody CommentDTO commentDTO) {
        return ResponseVO.success().add(commentService.commentComment(commentDTO));
    }

    @ApiOperation("点赞或取消点赞评论;更新评论信息，同时通过user id定位到user，更新user_like_comment数据库")
    @PostMapping("/like")
    public ResponseVO likeComment(@RequestParam Integer commentId,
                                  @RequestParam Integer likerId) {
        return ResponseVO.success().add(commentService.likeComment(commentId, likerId));
    }

    @ApiOperation("获得评论信息;通过commentId获得评论详细信息，通常和别的api配合使用")
    @GetMapping("/view/{commentId}")
    public ResponseVO getSpecificComment(@PathVariable Integer commentId) {
        return ResponseVO.success().add(commentService.getSpecificComment(commentId));
    }
}
