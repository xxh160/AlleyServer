package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("UserVO")
@Data
@NoArgsConstructor
public class UserVO {

    @ApiModelProperty("用户的唯一标识码")
    private Integer id;

    @ApiModelProperty("微信帐号的唯一标识码")
    private String openid; //微信账号唯一的标识码

    @ApiModelProperty("微信用户名")
    private String name;

    @ApiModelProperty("头像url")
    private String avatar;

    @ApiModelProperty("用户签名")
    private String sign;

    @ApiModelProperty("用户权限")
    private UserAuthVO auth;


    public UserVO(User user, UserAuthVO userAuthVO) {
        id = user.getId();
        openid = user.getOpenid();
        sign = user.getSign();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.auth = userAuthVO;
    }
}
