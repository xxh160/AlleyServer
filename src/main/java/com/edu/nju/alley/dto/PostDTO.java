package com.edu.nju.alley.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("PostDTO")
@Data
public class PostDTO {

    @ApiModelProperty("创建帖子的用户id")
    private Integer userId;

    @ApiModelProperty("帖子类型")
    private Integer labelId;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("帖子内容")
    private String content;

    @ApiModelProperty("锚点id")
    private Integer anchorId;

    @ApiModelProperty("经度")
    private Float longitude;

    @ApiModelProperty("纬度")
    private Float latitude;

    @ApiModelProperty("帖子权限")
    private PostAuthDTO auth;

    // 如果没有直接为null
    @ApiModelProperty("图片url,如果没有直接为null")
    private String pictureUrl;

}
