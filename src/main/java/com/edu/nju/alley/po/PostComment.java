package com.edu.nju.alley.po;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("帖子评论模型")
@Data
public class PostComment {

    private Integer id;

    private Integer userId;

    private Integer postId;

    private String content;

    private Integer likeNum;

}