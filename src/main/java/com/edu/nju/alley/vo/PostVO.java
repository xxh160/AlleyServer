package com.edu.nju.alley.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PostVO {
    private Integer id;


    private Integer authId;


    private Integer userId;


    private String title;


    private String content;


    private Integer likeNum;


    private Integer commentNum;


    private Date createT;


    private Date lastModifiedT;


    private Integer anchorId;


    private Integer addrX;


    private Integer addrY;

    private List<CommentVO> comments;

}
