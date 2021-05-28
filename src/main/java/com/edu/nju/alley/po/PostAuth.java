package com.edu.nju.alley.po;

import com.edu.nju.alley.dto.PostAuthDTO;
import lombok.Data;

@Data
public class PostAuth {

    private Integer id;

    private Boolean visible;

    private Boolean comment;

    public PostAuth(Integer postId, PostAuthDTO postAuthDTO) {
        this.id = postId;
        this.setVisible(postAuthDTO.isVisible());
        this.setComment(postAuthDTO.isComment());
    }

}