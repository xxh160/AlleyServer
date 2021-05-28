package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.ResponseVO;

public interface CommentService {
    ResponseVO commentComment(CommentDTO commentDTO);

    ResponseVO likeComment(Integer commentId, Integer likerId);

    CommentVO getComment(Integer commentId);
}
