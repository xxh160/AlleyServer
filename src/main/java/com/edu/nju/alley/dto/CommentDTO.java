package com.edu.nju.alley.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Integer userId;

    private Integer postId;

    private Integer fatherId;

    private String content;

}
