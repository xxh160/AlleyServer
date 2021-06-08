package com.edu.nju.alley.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("CommentDTO")
@Data
public class CommentDTO {

    @ApiModelProperty("评论者的id")
    private Integer userId;

    @ApiModelProperty("父级帖子的id，可以为null")
    private Integer postId;

    @ApiModelProperty("父级评论的id，可以为null")
    private Integer fatherId;

    @ApiModelProperty("评论内容")
    private String content;

}
