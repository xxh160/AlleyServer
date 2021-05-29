package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.UserAuth;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAuthVO {

    private boolean chat;

    private boolean mkfriend;

    private boolean locate;

    private boolean wxInfo;

    private boolean official;

    public UserAuthVO(UserAuth userAuth) {
        this.chat = userAuth.getChat();
        this.mkfriend = userAuth.getMakeFriend();
        this.locate = userAuth.getPosition();
        this.wxInfo = userAuth.getShowWxInfo();
        this.official = userAuth.getOfficial();
    }

}
