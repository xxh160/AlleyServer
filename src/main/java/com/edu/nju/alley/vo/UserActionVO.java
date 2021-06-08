package com.edu.nju.alley.vo;

import com.edu.nju.alley.enums.Type;
import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.po.UserLikePost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("UserActionVO")
@Data
@NoArgsConstructor
public class UserActionVO {

    @ApiModelProperty("作用的实体id")
    private Integer userId;

    @ApiModelProperty("动作类型")
    private Integer type; //0是post，1是comment

    @ApiModelProperty("被作用的实体的id")
    private Integer id; //被作用的实体id

    public UserActionVO(UserLikePost userLikePost) {
        this.userId = userLikePost.getUserId();
        this.type = Type.POST.getCode();
        this.id = userLikePost.getPostId();
    }

    public UserActionVO(UserLikeComment userLikeComment) {
        this.userId = userLikeComment.getUserId();
        this.type = Type.COMMENT.getCode();
        this.id = userLikeComment.getCommentId();
    }

    public static UserActionVO userCommentAPost(Integer userId, Integer postId) {
        UserActionVO userActionVO = new UserActionVO();
        userActionVO.setUserId(userId);
        userActionVO.setType(Type.POST.getCode());
        userActionVO.setId(postId);
        return userActionVO;
    }

    public static UserActionVO userCommentAComment(Integer userId, Integer commentId) {
        UserActionVO userActionVO = new UserActionVO();
        userActionVO.setUserId(userId);
        userActionVO.setType(Type.COMMENT.getCode());
        userActionVO.setId(commentId);
        return userActionVO;
    }
}
