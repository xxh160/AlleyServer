package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("PostIntroVO，post简介")
@Data
public class PostIntroVO {

    @ApiModelProperty("帖子id")
    private Integer id;

    @ApiModelProperty("帖子类型")
    private Integer labelId;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("帖子内容")
    private String content;
}
