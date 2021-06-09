package com.edu.nju.alley.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.edu.nju.alley.config.OSSConfig;
import com.edu.nju.alley.service.OSSService;
import com.edu.nju.alley.vo.OSSCallbackParamVO;
import com.edu.nju.alley.vo.OSSCallbackResultVO;
import com.edu.nju.alley.vo.OSSPolicyVO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class OSSServiceImpl implements OSSService {

    private final OSSConfig oss;

    private final OSSClient ossClient;

    @Autowired
    public OSSServiceImpl(OSSConfig oss,
                          OSSClient ossClient) {
        this.oss = oss;
        this.ossClient = ossClient;
    }

    @Override
    @SneakyThrows
    public OSSPolicyVO policy() {
        // 文件大小
        long maxSize = Long.parseLong(oss.getMaxSize()) * 1024 * 1024;
        // 存储目录
        String dir = oss.getDir();
        // 签名有效期
        Date expiration = new Date(System.currentTimeMillis() + 300 * 1000);

        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        // base64 encoding
        String policy = BinaryUtil.toBase64String(postPolicy.getBytes(StandardCharsets.UTF_8));

        // 提交节点
        String action = "https://" + oss.getBucketName() + "." + oss.getEndPoint();
        String signature = ossClient.calculatePostSignature(postPolicy);
        
        return new OSSPolicyVO(
                ossClient.getCredentialsProvider().getCredentials().getAccessKeyId(),
                policy,
                signature,
                dir,
                action,
                BinaryUtil.toBase64String(JSONUtil.parse(new OSSCallbackParamVO(oss.getCallback())).toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public OSSCallbackResultVO callback(String filename, String size, String mimeType, String width, String height) {
        String file = "https://".concat(oss.getBucketName()).concat(".").concat(oss.getEndPoint()).concat("/").concat(filename);
        return new OSSCallbackResultVO(file, size, mimeType, width, height);
    }
}
