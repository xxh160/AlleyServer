package com.edu.nju.alley.po;

import lombok.Data;

@Data
public class PostCommentRel {

    private Integer postId;


    private Integer commentId;

    public PostCommentRel(Integer postId, Integer commentId) {
        this.postId = postId;
        this.commentId = commentId;
    }

}