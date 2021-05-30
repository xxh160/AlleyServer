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

    public PostAuth(PostAuthDTO postAuthDTO) {
        this.updateByDTO(postAuthDTO);
    }

    public void updateByDTO(PostAuthDTO postAuthDTO) {
        this.visible = postAuthDTO.isVisible();
        this.comment = postAuthDTO.isComment();
    }

}