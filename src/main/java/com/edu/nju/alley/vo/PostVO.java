package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@ApiModel("PostVO")
@Data
@NoArgsConstructor
public class PostVO {

    @ApiModelProperty("帖子id")
    private Integer id;

    @ApiModelProperty("创建帖子的用户id")
    private Integer userId;

    @ApiModelProperty("帖子类型")
    private Integer labelId;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("帖子内容")
    private String content;

    @ApiModelProperty("帖子点赞数")
    private Integer likeNum;

    @ApiModelProperty("帖子评论数")
    private Integer commentNum;

    @ApiModelProperty("帖子创建时间")
    private Date createTime;

    @ApiModelProperty("锚点id")
    private Integer anchorId;

    @ApiModelProperty("经度")
    private Float longitude;

    @ApiModelProperty("纬度")
    private Float latitude;

    @ApiModelProperty("评论")
    private List<CommentVO> comments;

    @ApiModelProperty("帖子权限")
    private PostAuthVO auth;

    @ApiModelProperty("图片url,注意目前只能加一张")
    private String pictureUrl;

    @ApiModelProperty("是否有图片")
    private boolean hasPicture;

    public PostVO(Post post, List<CommentVO> comments, PostAuthVO postAuth) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.labelId = post.getLabelId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeNum = post.getLikeNum();
        this.commentNum = post.getCommentNum();
        this.createTime = post.getCreateT();
        this.anchorId = post.getAnchorId();
        this.longitude = post.getLongitude();
        this.latitude = post.getLatitude();
        this.comments = comments;
        this.auth = postAuth;
        this.pictureUrl = post.getPictureUrl();
        this.hasPicture = (this.pictureUrl != null);
    }

}
