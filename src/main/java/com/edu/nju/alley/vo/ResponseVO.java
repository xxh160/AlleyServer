package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel("ResponseVO")
@Data
@NoArgsConstructor
public class ResponseVO<T>{

    @ApiModelProperty("标识代码, 1表示成功，-1表示出错")
    public int code;

    @ApiModelProperty("提示信息")
    public String message;

    @ApiModelProperty("返回的数据")
    public T data;

    ResponseVO(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static <T> ResponseVO<T> success() {
        return new ResponseVO<T>(1, "successful");
    }

    public static <T> ResponseVO<T> failure() {
        return new ResponseVO<T>(-1, "failed");
    }

    public ResponseVO <T> add(T data) {
        this.data = data;
        return this;
    }

    public ResponseVO <T> code(int code) {
        this.code = code;
        return this;
    }

    public ResponseVO <T> msg(String message) {
        this.message = message;
        return this;
    }

}
