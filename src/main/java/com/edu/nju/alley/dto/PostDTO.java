package com.edu.nju.alley.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    private Integer userId;

    private Integer labelId;

    private String title;

    private String content;

    private Date createT;

    private Integer anchorId;

    private Integer longitude;

    private Integer latitude;

    private PostAuthDTO auth;

}
