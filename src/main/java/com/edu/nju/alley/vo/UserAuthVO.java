package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.UserAuth;

public class UserAuthVO {
    public UserAuthVO(UserAuth userAuth) {
        this.chat = userAuth.getChat();
        this.mkfriend = userAuth.getMakeFriend();
        this.locate = userAuth.getPosition();
        this.wxInfo = userAuth.getShowWxInfo();
        this.official = userAuth.getOfficial();
    }

    private boolean chat;
    private boolean mkfriend;
    private boolean locate;
    private boolean wxInfo;
    private boolean official;

}
