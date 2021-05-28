package com.edu.nju.alley.po;

import lombok.Data;

@Data
public class UserCommentRel {

    private Integer userId;


    private Integer commentId;

    public UserCommentRel(Integer userId, Integer commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}