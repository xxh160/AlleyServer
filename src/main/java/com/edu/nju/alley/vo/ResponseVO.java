package com.edu.nju.alley.vo;

import lombok.Getter;

@Getter
public class ResponseVO {

    public int code;

    public String message;

    public Object data;

    ResponseVO(int code, String message){
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static ResponseVO success(){
        return new ResponseVO(1, "successful");
    }

    public static ResponseVO failure(){
        return new ResponseVO(-1, "failed");
    }

    public ResponseVO add(Object data){
        this.data = data;
        return this;
    }

    public ResponseVO code(int code){
        this.code = code;
        return this;
    }

    public ResponseVO msg(String message){
        this.message = message;
        return this;
    }

}
