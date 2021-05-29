package com.edu.nju.alley.po;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRel {

    private Integer fatherId;

    private Integer childId;

    public CommentRel(Integer fatherId, Integer childId) {
        this.fatherId = fatherId;
        this.childId = childId;
    }

}