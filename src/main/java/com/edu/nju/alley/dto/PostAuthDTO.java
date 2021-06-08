package com.edu.nju.alley.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("PostAuthDTO")
@Data
public class PostAuthDTO {

    @ApiModelProperty("帖子是否可见?")
    private boolean visible;

    @ApiModelProperty("帖子是否可以评论?")
    private boolean comment;

}
