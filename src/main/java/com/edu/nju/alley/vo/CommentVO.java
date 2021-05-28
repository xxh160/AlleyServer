package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentVO {
    private Integer id;
    private Integer postId;
    private Integer fatherId;
    private String content;
    private Integer likeNum;
    private List<CommentVO> comments;

    public CommentVO(Comment comment, List<CommentVO> comments, Integer postId) {
        id = comment.getId();
        fatherId = comment.getUpperId();
        content = comment.getContent();
        likeNum = comment.getLikeNum();
        this.comments = comments;
    }
}
