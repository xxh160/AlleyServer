package com.edu.nju.alley.po;

import cn.hutool.core.date.DateUtil;
import com.edu.nju.alley.dto.PostDTO;
import lombok.Data;

import java.util.Date;

@Data
public class Post {

    private Integer id;


    private Integer authId;


    private Integer userId;


    private Integer labelId;


    private String title;


    private String content;


    private Integer likeNum;


    private Integer commentNum;


    private Date createT;


    private Date lastModifiedT;


    private Integer anchorId;


    private Integer addrX;


    private Integer addrY;

    //新建一个Post
    public Post(PostDTO postDTO) {
        this.id = 0;//怎么分配？
        this.authId = 0;//怎么分配？
        this.userId = postDTO.getUserId();
        this.labelId = postDTO.getLabelId();
        this.title = postDTO.getTitle();
        this.content = postDTO.getContent();
        this.likeNum = 0;
        this.commentNum = 0;
        this.createT = DateUtil.date();
        this.lastModifiedT = DateUtil.date();
        this.anchorId = postDTO.getAnchorId();
        this.addrX = postDTO.getAddrX();
        this.addrY = postDTO.getAddrY();
    }
}