package com.edu.nju.alley.vo;

import com.edu.nju.alley.enums.Type;
import com.edu.nju.alley.po.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentVO {

    private Integer id;

    private Integer userId;

    private Integer postId;

    private Integer fatherId;

    private String content;

    private Integer likeNum;

    private Date createTime;

    private List<CommentVO> comments;

    public CommentVO(Comment comment, Integer userId, List<CommentVO> comments) {
        this.id = comment.getId();
        this.userId = userId;
        if (comment.getUpperTypeId().equals(Type.COMMENT.getCode()))
            this.fatherId = comment.getUpperId();
        else this.postId = comment.getUpperId();
        this.content = comment.getContent();
        this.likeNum = comment.getLikeNum();
        this.comments = comments;
        this.createTime = comment.getCreateT();
    }

}
