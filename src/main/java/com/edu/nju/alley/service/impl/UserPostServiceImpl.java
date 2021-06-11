package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.UserLikePostMapper;
import com.edu.nju.alley.dao.UserPostRelMapper;
import com.edu.nju.alley.dao.support.UserLikePostDSS;
import com.edu.nju.alley.dao.support.UserPostRelDSS;
import com.edu.nju.alley.po.UserLikePost;
import com.edu.nju.alley.po.UserPostRel;
import com.edu.nju.alley.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class UserPostServiceImpl implements UserPostService {

    private final UserLikePostMapper userLikePostMapper;

    private final UserPostRelMapper userPostRelMapper;

    @Autowired
    public UserPostServiceImpl(UserLikePostMapper userLikePostMapper,
                               UserPostRelMapper userPostRelMapper) {
        this.userLikePostMapper = userLikePostMapper;
        this.userPostRelMapper = userPostRelMapper;
    }
    
    @Override
    public UserLikePost getSherUserLikePost(Integer userId, Integer postId) {
        Optional<UserLikePost> userLikePostOptional = userLikePostMapper
                .selectOne(c -> c.where(UserLikePostDSS.postId, isEqualTo(postId))
                        .and(UserLikePostDSS.userId, isEqualTo(userId)));
        return userLikePostOptional.orElse(null);
    }

    @Override
    public List<UserPostRel> getSherUserPostRel(Integer userId) {
        return userPostRelMapper.select(c -> c.where(UserPostRelDSS.userId, isEqualTo(userId)));
    }

    @Override
    public void deleteUserPostRelByPost(Integer postId) {
        userPostRelMapper.delete(c -> c.where(UserPostRelDSS.postId, isEqualTo(postId)));
    }

    @Override
    public void deleteUserLikePostByPost(Integer postId) {
        userLikePostMapper.delete(c -> c.where(UserLikePostDSS.userId, isEqualTo(postId)));
    }

    @Override
    public void deleteUserLikePost(Integer userId, Integer postId) {
        userLikePostMapper.delete(c -> c.where(UserPostRelDSS.userId, isEqualTo(userId))
                .and(UserLikePostDSS.postId, isEqualTo(postId)));
    }

    @Override
    public void insertUserLikePost(UserLikePost userLikePost) {
        userLikePostMapper.insert(userLikePost);
    }

    @Override
    public void insertUserPostRel(UserPostRel userPostRel) {
        userPostRelMapper.insert(userPostRel);
    }

    @Override
    public List<UserLikePost> getSherUserLikePost(Integer userId) {
        return userLikePostMapper.select(c -> c.where(UserLikePostDSS.userId, isEqualTo(userId)));
    }

    @Override
    public List<UserLikePost> getSherUserLikePostByPost(Integer postId) {
        return userLikePostMapper.select(c -> c.where(UserLikePostDSS.postId, isEqualTo(postId)));
    }

}
