package com.edu.nju.alley.service;

import com.edu.nju.alley.vo.PostViewVO;

import java.util.List;

public interface AnchorService {

    List<PostViewVO> getAllPosts(Integer anchorId, Integer pageId, Integer sort, Integer label);

}
