package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel("PostViewVO")
@Data
@AllArgsConstructor
public class PostViewVO {

    @ApiModelProperty("???")
    private Integer id;

    // 纬度
    @ApiModelProperty("纬度")
    private Float latitude;

    // 经度
    @ApiModelProperty("经度")
    private Float longitude;

}
