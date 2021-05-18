package com.edu.nju.alley.po;

import com.edu.nju.alley.enums.UpperType;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer id;


    private Integer userId;


    private Integer upperId;


    private UpperType upperType;


    private String content;


    private Integer likeNum;


    private Date createT;


    private Date lastModifiedT;

}