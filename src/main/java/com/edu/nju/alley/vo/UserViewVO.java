package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("UserViewVO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewVO {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("头像url")
    private String avatarUrl;

}