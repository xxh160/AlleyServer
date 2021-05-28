package com.edu.nju.alley.po;

import lombok.Data;

@Data
public class UserLikePost {

    private Integer userId;


    private Integer postId;

    public UserLikePost(Integer userId, Integer postId) {
        this.userId = userId;
        this.postId = postId;
    }

}