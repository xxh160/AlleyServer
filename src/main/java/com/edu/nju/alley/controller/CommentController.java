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

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public ResponseVO commentComment(@RequestBody CommentDTO commentDTO) {
        return ResponseVO.success().add(commentService.commentComment(commentDTO));
    }

    @PostMapping("/like")
    public ResponseVO likeComment(@RequestParam Integer commentId,
                                  @RequestParam Integer likerId) {
        return ResponseVO.success().add(commentService.likeComment(commentId, likerId));
    }

    @GetMapping("/view/{commentId}")
    public ResponseVO getSpecificComment(@PathVariable Integer commentId) {
        return ResponseVO.success().add(commentService.getSpecificComment(commentId));
    }
}
