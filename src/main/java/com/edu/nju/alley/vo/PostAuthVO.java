package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.PostAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("PostAuthVO")
@Data
@NoArgsConstructor
public class PostAuthVO {

    @ApiModelProperty("帖子是否可见")
    private boolean visible;

    @ApiModelProperty("帖子是否可评论")
    private boolean comment;

    public PostAuthVO(PostAuth postAuth) {
        this.visible = postAuth.getVisible();
        this.comment = postAuth.getComment();
    }

}
