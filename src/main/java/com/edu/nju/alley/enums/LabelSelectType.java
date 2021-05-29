package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum LabelSelectType {

    ALL(0, "全部");

    private final int code;

    private final String description;

    LabelSelectType(int code, String content) {
        this.code = code;
        this.description = content;
    }

}
