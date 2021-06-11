package com.edu.nju.alley.service;

import com.edu.nju.alley.po.UserCommentRel;
import com.edu.nju.alley.po.UserLikeComment;

import java.util.List;

public interface UserCommentService {

    UserCommentRel getSherUserCommentRel(Integer userId, Integer commentId);

    UserLikeComment getSherUserLikeComment(Integer userId, Integer commentId);

    List<UserCommentRel> getSherUserCommentRel(Integer userId);

    UserCommentRel getSherUserCommentRelByComment(Integer commentId);

    List<UserLikeComment> getSherUserLikeComment(Integer userId);

    List<UserLikeComment> getSherUserLikeCommentByComment(Integer commentId);

    void deleteUserCommentRelByComment(Integer commentId);

    void deleteUserLikeComment(Integer userId, Integer commentId);

    void deleteUserLikeCommentByComment(Integer commentId);

    void insertUserLikeComment(UserLikeComment userLikeComment);

    void insertUserCommentRel(UserCommentRel userCommentRel);

}
