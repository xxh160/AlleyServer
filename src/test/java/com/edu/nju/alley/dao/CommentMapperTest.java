package com.edu.nju.alley.dao;

import cn.hutool.core.date.DateUtil;
import com.edu.nju.alley.po.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CommentMapperTest {

    private final CommentMapper commentMapper;

    public Comment defaultComment() {
        Comment comment = new Comment();
        comment.setUserId(1);
        comment.setContent("test");
        comment.setCreateT(DateUtil.date());
        comment.setLastModifiedT(DateUtil.date());
        comment.setLikeNum(0);
        comment.setUpperTypeId(0);
        comment.setUpperId(0);
        return comment;
    }

    @Autowired
    public CommentMapperTest(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Test
    public void InsertTest() {
        Comment a = defaultComment();
        this.commentMapper.insert(a);
        assertNotNull(a.getId());
    }

    @Test
    public void InsertMultipleTest() {
        Comment a = defaultComment();
        Comment b = defaultComment();
        this.commentMapper.insertMultiple(Arrays.asList(a, b));
    }

}
