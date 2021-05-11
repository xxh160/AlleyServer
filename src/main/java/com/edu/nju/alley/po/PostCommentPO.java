package com.edu.nju.alley.po;

import javax.annotation.Generated;

public class PostCommentPO {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer postId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer likeNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getUserId() {
        return userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getPostId() {
        return postId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getContent() {
        return content;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getLikeNum() {
        return likeNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }
}