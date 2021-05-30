package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PostVO {

    private Integer id;

    private Integer userId;

    private Integer labelId;

    private String title;

    private String content;

    private Integer likeNum;

    private Integer commentNum;

    private Date createTime;

    private Integer anchorId;

    private Float longitude;

    private Float latitude;

    private List<CommentVO> comments;

    private PostAuthVO auth;

    private String pictureUrl;

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
