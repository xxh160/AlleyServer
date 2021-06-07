package com.edu.nju.alley.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.edu.nju.alley.config.OSSConfig;
import com.edu.nju.alley.service.OSSService;
import com.edu.nju.alley.vo.OSSCallbackParamVO;
import com.edu.nju.alley.vo.OSSCallbackResultVO;
import com.edu.nju.alley.vo.OSSPolicyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OSSServiceImpl implements OSSService {

    private final OSSConfig.OSS oss;

    private final OSSClient ossClient;

    //zzh 2021/6/7 17:48
    private static final Logger LOGGER = LoggerFactory.getLogger(OSSServiceImpl.class);

    //zzh 2021/6/7 17:48


    @Autowired
    public OSSServiceImpl(OSSConfig.OSS oss,
                          OSSClient ossClient) {
        this.oss = oss;
        this.ossClient = ossClient;
    }

    @Override
    public OSSPolicyVO policy() {
        //zzh 2021/6/7 17:50
        OSSPolicyVO result=new OSSPolicyVO();

        //存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = oss.getDir()+sdf.format(new Date());//引用了util的Date

        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + 300 * 1000;
        Date expiration = new Date(expireEndTime);

        // 文件大小
        long maxSize = oss.getMaxSize() * 1024 * 1024;

        //回调
        OSSCallbackParamVO callback=new OSSCallbackParamVO();
        callback.setCallbackUrl(oss.getCallback());
        callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callback.setCallbackBodyType("application/x-www-form-urlencoded");
        //提交节点
        String action = "http://" + oss.getBucketName() + "." + oss.getEndPoint();

        try{
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy =ossClient.generatePostPolicy(expiration,policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            // 返回结果
            result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callback);
            result.setHost(action);


        }catch (Exception e) {
            LOGGER.error("签名生成失败", e);
        }

        return result;
        //zzh 2021/6/7 17:50
    }

    @Override
    public OSSCallbackResultVO callback(HttpServletRequest request) {
        OSSCallbackResultVO result=new OSSCallbackResultVO();
        String filename = request.getParameter("filename");
        filename = "http://".concat(oss.getBucketName()).concat(".").concat(oss.getEndPoint()).concat("/").concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }
}
