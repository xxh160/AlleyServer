package com.edu.nju.alley.po;

import java.util.Date;
import javax.annotation.Generated;

public class Post {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String label;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String title;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer likeNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer commentNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean isPublic;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer addrX;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer addrY;

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
    public String getLabel() {
        return label;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getTitle() {
        return title;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getCommentNum() {
        return commentNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getTime() {
        return time;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setTime(Date time) {
        this.time = time;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getIsPublic() {
        return isPublic;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getAddrX() {
        return addrX;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAddrX(Integer addrX) {
        this.addrX = addrX;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getAddrY() {
        return addrY;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAddrY(Integer addrY) {
        this.addrY = addrY;
    }
}