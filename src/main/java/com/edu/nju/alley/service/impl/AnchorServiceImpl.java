package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.service.AnchorService;
import com.edu.nju.alley.vo.ResponseVO;
import org.springframework.stereotype.Service;

@Service
public class AnchorServiceImpl implements AnchorService {

    @Override
    public ResponseVO getAllPosts(Integer anchorId,
                                  Integer pageId,
                                  Integer sort,
                                  Integer label) {
        return null;
    }
}
