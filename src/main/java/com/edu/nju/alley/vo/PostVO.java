package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.po.PostAuth;

import java.util.Date;
import java.util.List;

public class PostVO {
    private Integer userId;
    private Integer labelId;
    private String title;
    private String content;
    private Integer likeNum;
    private Integer CommentNum;
    private Date createTime;
    private Integer anchorId;
    private Integer addrX;
    private Integer addrY;
    private List<CommentVO> comments;
    private PostAuthVO auth;


    public PostVO(Post post, List<CommentVO> comments, PostAuth postAuth) {
        userId = post.getUserId();
        labelId = post.getLabelId();
        title = post.getTitle();
        content = post.getContent();
        likeNum = post.getLikeNum();
        CommentNum = post.getCommentNum();
        createTime = post.getCreateT();
        anchorId = post.getAnchorId();
        addrX = post.getAddrX();
        addrY = post.getAddrY();
        this.comments = comments;
        this.auth = new PostAuthVO(postAuth);
    }

    
}
