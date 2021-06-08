package com.edu.nju.alley.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("AuthenticationDTO")
@Data
public class AuthenticationDTO {

    @ApiModelProperty("邀请码")
    private String code;

}
