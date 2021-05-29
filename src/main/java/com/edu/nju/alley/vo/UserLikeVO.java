package com.edu.nju.alley.vo;

import com.edu.nju.alley.enums.LikeType;
import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.po.UserLikePost;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLikeVO {

    private Integer type; //1是post，2是comment

    private Integer id; //被点赞的实体id

    public UserLikeVO(UserLikePost userLikePost) {
        this.type = LikeType.POST.getCode();
        this.id = userLikePost.getPostId();
    }

    public UserLikeVO(UserLikeComment userLikeComment) {
        this.type = LikeType.COMMENT.getCode();
        this.id = userLikeComment.getCommentId();
    }
}
