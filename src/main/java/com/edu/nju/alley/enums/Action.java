package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum Action {

    LIKE(0, "点赞"),
    COMMENT(1, "评论");

    private final int code;

    private final String description;

    Action(int code, String content) {
        this.code = code;
        this.description = content;
    }

}
