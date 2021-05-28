package com.edu.nju.alley.po;

import lombok.Data;

@Data
public class UserLikeComment {

    private Integer userId;


    private Integer commentId;

    public UserLikeComment(Integer userId, Integer commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }


}