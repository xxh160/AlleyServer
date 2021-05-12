package com.edu.nju.alley.po;

import lombok.Data;

@Data
public class PostCommentPO {

    private Integer id;

    private Integer userId;

    private Integer postId;

    private String content;

    private Integer likeNum;

}