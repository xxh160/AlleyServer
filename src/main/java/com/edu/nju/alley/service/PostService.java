package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.vo.ResponseVO;


public interface PostService {

    ResponseVO getSpecialPost(Integer postId);

    ResponseVO updatePost(Integer postId, PostDTO postDTO);

    ResponseVO likePost(Integer postId, Integer likerId);

    ResponseVO commentPost(CommentDTO commentDTO);

    ResponseVO createPost(PostDTO postDTO);

    ResponseVO deletePost(Integer postId);

}
