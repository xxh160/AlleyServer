package com.edu.nju.alley.vo;

import com.edu.nju.alley.enums.Type;
import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.po.UserLikePost;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserActionVO {

    private Integer userId;

    private Integer type; //1是post，2是comment

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
