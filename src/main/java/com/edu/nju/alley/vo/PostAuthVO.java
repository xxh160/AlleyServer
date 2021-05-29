package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.PostAuth;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PostAuthVO {

    private boolean visible;
   
    private boolean comment;

    public PostAuthVO(PostAuth postAuth) {
        this.visible = postAuth.getVisible();
        this.comment = postAuth.getComment();
    }

}
