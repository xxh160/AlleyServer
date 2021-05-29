package com.edu.nju.alley.po;

import com.edu.nju.alley.dto.PostAuthDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostAuth {

    private Integer id;

    private Boolean visible;

    private Boolean comment;

    public PostAuth(Integer postId, PostAuthDTO postAuthDTO) {
        this.id = postId;
        this.setVisible(postAuthDTO.isVisible());
        this.setComment(postAuthDTO.isComment());
    }

    public void updateByDTO(PostAuthDTO postAuthDTO) {
        this.visible = postAuthDTO.isVisible();
        this.comment = postAuthDTO.isComment();
    }

}