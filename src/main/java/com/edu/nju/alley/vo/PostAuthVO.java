package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.PostAuth;

public class PostAuthVO {
    public PostAuthVO(PostAuth postAuth) {
        this.visible = postAuth.getVisible();
        this.comment = postAuth.getComment();
    }

   
    private boolean visible;
    private boolean comment;
}
