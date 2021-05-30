package com.edu.nju.alley.po;

import com.edu.nju.alley.dto.UserAuthDTO;
import lombok.Data;

@Data
public class UserAuth {

    private Integer id;

    private Boolean chat;

    private Boolean position;

    private Boolean makeFriend;

    private Boolean showWxInfo;

    private Boolean official;

    public void updateByDTO(UserAuthDTO userAuthDTO) {
        this.chat = userAuthDTO.isChat();
        this.position = userAuthDTO.isLocate();
        this.makeFriend = userAuthDTO.isMkfriend();
        this.showWxInfo = userAuthDTO.isWxInfo();
        this.official = userAuthDTO.isOfficial();
    }

    public static UserAuth defaultUserAuth() {
        UserAuth userAuth = new UserAuth();
        userAuth.setChat(false);
        userAuth.setPosition(false);
        userAuth.setMakeFriend(false);
        userAuth.setOfficial(false);
        userAuth.setShowWxInfo(false);
        return userAuth;
    }

}