package com.edu.nju.alley.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("UserAuthDTO")
@Data
public class UserAuthDTO {

    @ApiModelProperty("是否可以被聊天")
    private boolean chat;

    @ApiModelProperty("是否可以被加为好友")
    private boolean mkfriend;

    @ApiModelProperty("是否展示位置")
    private boolean locate;

    @ApiModelProperty("是否展示微信信息")
    private boolean wxInfo;

    @ApiModelProperty("是否为官方用户")
    private boolean official;

}
