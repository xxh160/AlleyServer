package com.edu.nju.alley.po;

import lombok.Data;

@Data
public class UserPostRel {

    private Integer userId;

    private Integer postId;

    public UserPostRel(Integer userId, Integer postId) {
        this.userId = userId;
        this.postId = postId;
    }

}