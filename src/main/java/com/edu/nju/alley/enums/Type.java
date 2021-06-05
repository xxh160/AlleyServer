package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum Type {
    // 各种方法，包括<点赞>和<检查是否为点赞>的type_id
    // 涉及到post和comment的表示的均使用这种id
    POST(0), COMMENT(1);

    private final int code;

    Type(int code) {
        this.code = code;
    }

}
