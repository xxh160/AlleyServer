package com.edu.nju.alley.service.impl;

import com.aliyun.oss.OSSClient;
import com.edu.nju.alley.config.OSSConfig;
import com.edu.nju.alley.service.OSSService;
import com.edu.nju.alley.vo.OSSCallbackResultVO;
import com.edu.nju.alley.vo.OSSPolicyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OSSServiceImpl implements OSSService {

    private final OSSConfig ossConfig;

    private final OSSClient ossClient;

    @Autowired
    public OSSServiceImpl(OSSConfig ossConfig,
                          OSSClient ossClient) {
        this.ossConfig = ossConfig;
        this.ossClient = ossClient;
    }

    @Override
    public OSSPolicyVO policy() {
        return null;
    }

    @Override
    public OSSCallbackResultVO callback() {
        return null;
    }
}
