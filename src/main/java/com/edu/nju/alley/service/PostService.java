package com.edu.nju.alley.service;

import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.vo.ResponseVO;

import java.util.Optional;

public interface PostService {
    ResponseVO getPost(Integer id);

}
