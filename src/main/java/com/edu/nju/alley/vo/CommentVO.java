package com.edu.nju.alley.vo;

import com.edu.nju.alley.enums.Type;
import com.edu.nju.alley.po.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@ApiModel("CommentVO")
@Data
@NoArgsConstructor
public class CommentVO {

    @ApiModelProperty("评论id")
    private Integer id;

    @ApiModelProperty("评论者id")
    private Integer userId;

    @ApiModelProperty("父级帖子的id，可以为null")
    private Integer postId;

    @ApiModelProperty("父级评论的id，可以为null")
    private Integer fatherId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论点赞数")
    private Integer likeNum;

    @ApiModelProperty("评论创建时间")
    private Date createTime;

    @ApiModelProperty("评论的评论")
    private List<CommentVO> comments;

    public CommentVO(Comment comment, Integer userId, List<CommentVO> comments) {
        this.id = comment.getId();
        this.userId = userId;
        if (comment.getUpperTypeId().equals(Type.COMMENT.getCode()))
            this.fatherId = comment.getUpperId();
        else this.postId = comment.getUpperId();
        this.content = comment.getContent();
        this.likeNum = comment.getLikeNum();
        this.comments = comments;
        this.createTime = comment.getCreateT();
    }

}
