package com.edu.nju.alley.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostViewVO {

    private Integer id;

    // 纬度
    private Float latitude;

    // 经度
    private Float longitude;

}
