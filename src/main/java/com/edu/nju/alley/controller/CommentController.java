package com.edu.nju.alley.controller;


import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.LikeVO;
import com.edu.nju.alley.vo.NewRecordVO;
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

    @ApiOperation("评论一条评论")
    @PostMapping("/comment")
    public ResponseVO<NewRecordVO> commentComment(@RequestBody CommentDTO commentDTO) {
        return ResponseVO.<NewRecordVO>success().add(commentService.commentComment(commentDTO));
    }

    @ApiOperation("点赞或取消点赞评论；由后端动态决定是点赞还是取消点赞")
    @PostMapping("/like")
    public ResponseVO<LikeVO> likeComment(@RequestParam Integer commentId,
                                          @RequestParam Integer likerId) {
        return ResponseVO.<LikeVO>success().add(commentService.likeComment(commentId, likerId));
    }

    @ApiOperation("获得特定评论")
    @GetMapping("/view/{commentId}")
    public ResponseVO<CommentVO> getSpecificComment(@PathVariable Integer commentId) {
        return ResponseVO.<CommentVO>success().add(commentService.getSpecificComment(commentId));
    }
}
