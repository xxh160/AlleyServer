package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.UserAuth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("UserAuthVO")
@Data
@NoArgsConstructor
public class UserAuthVO {

    @ApiModelProperty("是否能被发起聊天")
    private boolean chat;

    @ApiModelProperty("是否能被添加好友")
    private boolean mkfriend;

    @ApiModelProperty("是否共享位置")
    private boolean locate;

    @ApiModelProperty("是否展示微信信息")
    private boolean wxInfo;

    @ApiModelProperty("是否是官方用户")
    private boolean official;

    public UserAuthVO(UserAuth userAuth) {
        this.chat = userAuth.getChat();
        this.mkfriend = userAuth.getMakeFriend();
        this.locate = userAuth.getPosition();
        this.wxInfo = userAuth.getShowWxInfo();
        this.official = userAuth.getOfficial();
    }

}
