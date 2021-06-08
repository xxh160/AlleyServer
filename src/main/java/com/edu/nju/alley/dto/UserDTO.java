package com.edu.nju.alley.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("UserDTO")
@Data
public class UserDTO {

    @ApiModelProperty("用户签名")
    private String sign;

    @ApiModelProperty("用户权限")
    private UserAuthDTO auth;

}
