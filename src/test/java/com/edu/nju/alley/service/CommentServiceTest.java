package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.vo.CommentVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTest {

    private final CommentService commentService;

    private CommentDTO defaultCommentDTO() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent("Test");
        commentDTO.setFatherId(1);
        commentDTO.setUserId(1);
        return commentDTO;
    }

    @Autowired
    public CommentServiceTest(CommentService commentService) {
        this.commentService = commentService;
    }

    @Test
    public void commentCommentTest() {
        commentService.commentComment(defaultCommentDTO());
    }

    @Test
    public void getSpecificOneTest() {
        CommentVO commentVO = commentService.getSpecificOne(1);
    }

}
