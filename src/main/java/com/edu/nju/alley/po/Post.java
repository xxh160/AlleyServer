package com.edu.nju.alley.po;

import lombok.Data;

import java.util.Date;

@Data
public class Post {

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

}