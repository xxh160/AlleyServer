package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("NewRecordVO")
@Data
@NoArgsConstructor
public class NotificationVO {

    @ApiModelProperty("notification的id 主键")
    Integer id;

    @ApiModelProperty("被通知人的id")
    Integer userId;

    @ApiModelProperty("动作类型 0是点赞 1是评论 或许以后会有增多的类型")
    Integer action_type;

    @ApiModelProperty("生成通知的时间")
    Date create_t;

    @ApiModelProperty("是否已经读到这条通知")
    boolean have_read;

    @ApiModelProperty("action.userId是谁点赞/评论了 action.type是什么类型的东西被点赞/评论了 action.id是哪个东西被点赞/评论了")
    UserActionVO action;

}
