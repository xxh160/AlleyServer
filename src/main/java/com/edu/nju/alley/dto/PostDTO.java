package com.edu.nju.alley.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {
    private Integer userId;
    private Integer labelId;
    private String title;
    private String content;
    private Date createTime;
    private Integer anchorId;
    private Integer addrX;
    private Integer addrY;
    private PostAuthDTO auth;

}
