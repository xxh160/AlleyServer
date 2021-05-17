package com.edu.nju.alley.po;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer id;


    private Integer userId;


    private Integer upperId;


    private String upperType;


    private String content;


    private Integer likeNum;


    private Date createT;


    private Date lastModifiedT;
    
}