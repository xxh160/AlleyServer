package com.edu.nju.alley.po;

import javax.annotation.Generated;

public class User {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String gender;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String personalIntroduction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean allowChat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean allowFriendRequest;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Boolean allowSharePosition;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getGender() {
        return gender;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPersonalIntroduction() {
        return personalIntroduction;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPersonalIntroduction(String personalIntroduction) {
        this.personalIntroduction = personalIntroduction == null ? null : personalIntroduction.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getAllowChat() {
        return allowChat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAllowChat(Boolean allowChat) {
        this.allowChat = allowChat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getAllowFriendRequest() {
        return allowFriendRequest;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAllowFriendRequest(Boolean allowFriendRequest) {
        this.allowFriendRequest = allowFriendRequest;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Boolean getAllowSharePosition() {
        return allowSharePosition;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAllowSharePosition(Boolean allowSharePosition) {
        this.allowSharePosition = allowSharePosition;
    }
}