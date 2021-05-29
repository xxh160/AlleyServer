package com.edu.nju.alley.vo;

import com.edu.nju.alley.enums.CommentUpperType;
import com.edu.nju.alley.po.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentVO {

    private Integer id;

    private Integer postId;

    private Integer fatherId;

    private String content;

    private Integer likeNum;

    private List<CommentVO> comments;

    public CommentVO(Comment comment, List<CommentVO> comments) {
        this.id = comment.getId();
        if (comment.getUpperTypeId().equals(CommentUpperType.COMMENT.getCode()))
            this.fatherId = comment.getUpperId();
        else this.postId = comment.getUpperId();
        this.content = comment.getContent();
        this.likeNum = comment.getLikeNum();
        this.comments = comments;
    }

}
