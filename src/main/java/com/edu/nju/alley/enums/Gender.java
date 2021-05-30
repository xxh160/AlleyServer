package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum Gender {

    UNKNOWN(0),
    MALE(1),
    FEMALE(2);

    private final int code;

    Gender(int code) {
        this.code = code;
    }
    
}
