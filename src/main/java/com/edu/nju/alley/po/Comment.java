package com.edu.nju.alley.po;

import cn.hutool.core.date.DateUtil;
import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.enums.CommentUpperType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@NoArgsConstructor
public class Comment {

    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private Integer userId;

    private Integer upperId;

    private Integer upperTypeId;

    private String content;

    private Integer likeNum;

    private Date createT;

    private Date lastModifiedT;

    // 创建一个新的评论对象，可能是子评论，可能是帖子评论
    public Comment(CommentDTO commentDTO, Integer typeId) {
        this.userId = commentDTO.getUserId();
        this.upperTypeId = typeId;
        if (typeId.equals(CommentUpperType.POST.getCode()))
            this.upperId = commentDTO.getPostId();
        else this.upperId = commentDTO.getFatherId();
        this.content = commentDTO.getContent();
        this.likeNum = 0;
        this.createT = DateUtil.date();
        this.lastModifiedT = DateUtil.date();
    }

    public static Comment PostComment(CommentDTO commentDTO) {
        return new Comment(commentDTO, CommentUpperType.POST.getCode());
    }

    public static Comment AdvancedComment(CommentDTO commentDTO) {
        return new Comment(commentDTO, CommentUpperType.COMMENT.getCode());
    }

}