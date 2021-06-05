package com.edu.nju.alley.service;

import com.edu.nju.alley.vo.OSSCallbackResultVO;
import com.edu.nju.alley.vo.OSSPolicyVO;

public interface OSSService {

    OSSPolicyVO policy();

    OSSCallbackResultVO callback();

}
