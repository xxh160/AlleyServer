package com.edu.nju.alley.service;

import com.edu.nju.alley.po.UserLikePost;
import com.edu.nju.alley.po.UserPostRel;

import java.util.List;

public interface UserPostService {

    UserLikePost getSherUserLikePost(Integer userId, Integer postId);

    List<UserLikePost> getSherUserLikePost(Integer userId);

    List<UserLikePost> getSherUserLikePostByPost(Integer postId);

    List<UserPostRel> getSherUserPostRel(Integer userId);

    void deleteUserPostRelByPost(Integer postId);

    void deleteUserLikePostByPost(Integer postId);

    void deleteUserLikePost(Integer userId, Integer postId);

    void insertUserLikePost(UserLikePost userLikePost);

    void insertUserPostRel(UserPostRel userPostRel);

}
