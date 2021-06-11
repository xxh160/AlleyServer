package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum Label {

    ALL(0, "全部"),
    ESSAY(1, "随笔"),
    NOTIFICATION(2, "通知"),
    FEEDBACK(3, "反馈"),
    SELF(4, "仅看自己");

    private final int code;

    private final String description;

    Label(int code, String content) {
        this.code = code;
        this.description = content;
    }

}
