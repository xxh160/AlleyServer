package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum LikeType {
    // <点赞>和<检查是否为点赞>的type_id
    POST(0), COMMENT(1);

    private final int code;

    LikeType(int code) {
        this.code = code;
    }

}
