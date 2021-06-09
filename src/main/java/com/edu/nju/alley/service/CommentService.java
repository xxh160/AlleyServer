package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.po.Comment;
import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.LikeVO;
import com.edu.nju.alley.vo.NewRecordVO;

import java.util.List;

public interface CommentService {

    NewRecordVO commentComment(CommentDTO commentDTO);

    LikeVO likeComment(Integer commentId, Integer likerId);

    CommentVO getSpecificComment(Integer commentId);

    void insertOne(Comment comment);

    Comment getSherComment(Integer commentId);

    List<Comment> getChildComments(Integer commentId);

    Post getOriginPost(Integer commentId);

}
