package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum Sort {

    TIME(1), HOT(2);

    private final int code;

    Sort(int code) {
        this.code = code;
    }

}
