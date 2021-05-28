package com.edu.nju.alley.po;

import cn.hutool.core.date.DateUtil;
import com.edu.nju.alley.dto.CommentDTO;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer id;


    private Integer userId;


    private Integer upperId;


    private Integer upperTypeId;


    private String content;


    private Integer likeNum;


    private Date createT;


    private Date lastModifiedT;

    public Comment(CommentDTO commentDTO, Integer typeId) {
        this.id = 0;//怎么分配？
        this.userId = commentDTO.getUserId();
        this.upperId = commentDTO.getFatherId();
        this.upperTypeId = typeId;
        this.content = commentDTO.getContent();
        this.likeNum = 0;
        this.createT = DateUtil.date();
        this.lastModifiedT = DateUtil.date();
    }


}