package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("api response format")
@Data
@NoArgsConstructor
public class ResponseVO {

    @ApiModelProperty("标识代码, 1表示成功，-1表示出错")
    public int code;

    @ApiModelProperty("提示信息")
    public String message;

    @ApiModelProperty("返回的数据")
    public Object data;

    ResponseVO(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static ResponseVO success() {
        return new ResponseVO(1, "successful");
    }

    public static ResponseVO failure() {
        return new ResponseVO(-1, "failed");
    }

    public ResponseVO add(Object data) {
        this.data = data;
        return this;
    }

    public ResponseVO code(int code) {
        this.code = code;
        return this;
    }

    public ResponseVO msg(String message) {
        this.message = message;
        return this;
    }

}
