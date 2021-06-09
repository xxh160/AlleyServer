package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("PostIntroVO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostIntroVO {

    @ApiModelProperty("帖子id")
    private Integer id;

    @ApiModelProperty("帖子类型")
    private Integer labelId;

    @ApiModelProperty("帖子标题")
    private String title;

    @ApiModelProperty("帖子内容")
    private String content;

    public PostIntroVO(Post post) {
        this.id = post.getId();
        this.labelId = post.getLabelId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

}
