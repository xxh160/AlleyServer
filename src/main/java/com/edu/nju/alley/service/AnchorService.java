package com.edu.nju.alley.service;

import com.edu.nju.alley.vo.ResponseVO;

public interface AnchorService {

    ResponseVO getAllPosts(Integer anchorId,
                           Integer pageId,
                           Integer sort,
                           Integer label);

}
