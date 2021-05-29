package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum SortType {

    TIME(1), HOT(2);

    private final int code;

    SortType(int code) {
        this.code = code;
    }

}
