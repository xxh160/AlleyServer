package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum Labels {

    ALL(0, "全部"),
    ESSAY(1, "随笔"),
    NOTIFICATION(2, "通知"),
    FEEDBACK(3, "反馈");

    private final int code;

    private final String description;

    Labels(int code, String content) {
        this.code = code;
        this.description = content;
    }

}
