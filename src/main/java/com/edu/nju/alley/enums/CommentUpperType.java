package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum CommentUpperType {

    POST(1), COMMENT(2);

    private final int code;

    CommentUpperType(int code) {
        this.code = code;
    }
}
