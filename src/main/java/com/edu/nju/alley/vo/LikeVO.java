package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.po.UserLikePost;

public class LikeVO {
    private Integer type;//1是post，2是comment
    private Integer id;//被点赞的实体id

    public LikeVO(UserLikePost userLikePost) {
        this.type = 1;
        this.id = userLikePost.getPostId();
    }

    public LikeVO(UserLikeComment userLikeComment) {
        this.type = 2;
        this.id = userLikeComment.getCommentId();
    }
}
