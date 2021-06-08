package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OSSCallbackParamVO {

    @ApiModelProperty("请求的回调地址")
    private String callbackUrl;

    @ApiModelProperty("回调是传入request中的参数")
    private String callbackBody;

    @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;

    public OSSCallbackParamVO(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        this.callbackBody = "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}";
        this.callbackBodyType = "application/x-www-form-urlencoded";
    }

}
