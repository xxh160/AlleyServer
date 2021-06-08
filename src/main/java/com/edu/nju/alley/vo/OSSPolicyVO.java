package com.edu.nju.alley.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OSSPolicyVO {

    @ApiModelProperty("访问身份验证中用到用户标识")
    private String accessKeyId;

    @ApiModelProperty("用户表单上传的策略,经过base64编码过的字符串")
    private String policy;

    @ApiModelProperty("对policy签名后的字符串")
    private String signature;

    @ApiModelProperty("上传文件夹路径前缀")
    private String dir;

    @ApiModelProperty("oss对外服务的访问域名")
    private String host;

    @ApiModelProperty("上传成功后的回调设置")
    private OSSCallbackParamVO callback;

}
