package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.PostMapper;
import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper postmapper;

    @Override
    public ResponseVO getPost(Integer id) {
        Optional<Post> postOptional = postmapper.selectByPrimaryKey(id);
        if (postOptional.isEmpty()) return ResponseVO.failure();
        Post post = postOptional.get();


        return ResponseVO.success();
    }
}
