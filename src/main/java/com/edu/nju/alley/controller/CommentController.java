package com.edu.nju.alley.controller;


import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "comment")
@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment")
    public ResponseVO commentComment(@RequestBody CommentDTO commentDTO) {
        return commentService.commentComment(commentDTO);
    }

    @GetMapping("/like")
    public ResponseVO likeComment(@RequestParam Integer commentId, @RequestParam Integer likerId) {
        return commentService.likeComment(commentId, likerId);
    }
}
