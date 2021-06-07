package com.edu.nju.alley.service;

import com.edu.nju.alley.vo.OSSCallbackResultVO;
import com.edu.nju.alley.vo.OSSPolicyVO;

import javax.servlet.http.HttpServletRequest;

public interface OSSService {

    OSSPolicyVO policy();

    OSSCallbackResultVO callback(HttpServletRequest request);

}
