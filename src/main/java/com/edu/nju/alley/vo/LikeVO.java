package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("LikeVO")
@Data
@NoArgsConstructor
public class LikeVO {

    @ApiModelProperty("用户是点赞还是取消点赞")
    private boolean isLike;

    public LikeVO(boolean isLike) {
        this.isLike = isLike;
    }

}
