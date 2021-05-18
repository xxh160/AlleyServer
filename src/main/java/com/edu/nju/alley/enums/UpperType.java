package com.edu.nju.alley.enums;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum UpperType {
    Post("Post"), Comment("Comment");

    private final String value;

}
