package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.UserCommentRelMapper;
import com.edu.nju.alley.dao.UserLikeCommentMapper;
import com.edu.nju.alley.dao.support.UserCommentRelDSS;
import com.edu.nju.alley.dao.support.UserLikeCommentDSS;
import com.edu.nju.alley.po.UserCommentRel;
import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class UserCommentServiceImpl implements UserCommentService {

    private final UserCommentRelMapper userCommentRelMapper;

    private final UserLikeCommentMapper userLikeCommentMapper;

    @Autowired
    public UserCommentServiceImpl(UserCommentRelMapper userCommentRelMapper,
                                  UserLikeCommentMapper userLikeCommentMapper) {
        this.userCommentRelMapper = userCommentRelMapper;
        this.userLikeCommentMapper = userLikeCommentMapper;
    }

    @Override
    public UserCommentRel getSherUserCommentRel(Integer userId, Integer commentId) {
        Optional<UserCommentRel> userCommentRelOptional = userCommentRelMapper
                .selectOne(c -> c.where(UserCommentRelDSS.commentId, isEqualTo(commentId))
                        .and(UserCommentRelDSS.userId, isEqualTo(userId)));
        return userCommentRelOptional.orElse(null);
    }

    @Override
    public UserLikeComment getSherUserLikeComment(Integer userId, Integer commentId) {
        Optional<UserLikeComment> userLikeCommentOptional = userLikeCommentMapper
                .selectOne(c -> c.where(UserLikeCommentDSS.commentId, isEqualTo(commentId))
                        .and(UserLikeCommentDSS.userId, isEqualTo(userId)));
        return userLikeCommentOptional.orElse(null);
    }

    @Override
    public List<UserCommentRel> getSherUserCommentRel(Integer userId) {
        return userCommentRelMapper.select(c -> c.where(UserCommentRelDSS.userId, isEqualTo(userId)));
    }

    // 评论只有一个作者
    @Override
    public UserCommentRel getSherUserCommentRelByComment(Integer commentId) {
        Optional<UserCommentRel> userCommentRelOptional = userCommentRelMapper
                .selectOne(c -> c.where(UserCommentRelDSS.commentId, isEqualTo(commentId)));
        return userCommentRelOptional.orElse(null);
    }


    @Override
    public List<UserLikeComment> getSherUserLikeComment(Integer userId) {
        return userLikeCommentMapper.select(c -> c.where(UserLikeCommentDSS.userId, isEqualTo(userId)));
    }

    @Override
    public List<UserLikeComment> getSherUserLikeCommentByComment(Integer commentId) {
        return userLikeCommentMapper.select(c -> c.where(UserLikeCommentDSS.commentId, isEqualTo(commentId)));
    }

    @Override
    public void deleteUserCommentRelByComment(Integer commentId) {
        userCommentRelMapper.delete(c -> c.where(UserCommentRelDSS.commentId, isEqualTo(commentId)));
    }

    @Override
    public void deleteUserLikeComment(Integer userId, Integer commentId) {
        userLikeCommentMapper.delete(c -> c.where(UserLikeCommentDSS.commentId, isEqualTo(commentId))
                .and(UserLikeCommentDSS.userId, isEqualTo(userId)));
    }

    @Override
    public void deleteUserLikeCommentByComment(Integer commentId) {
        userLikeCommentMapper.delete(c -> c.where(UserLikeCommentDSS.commentId, isEqualTo(commentId))
                .and(UserLikeCommentDSS.commentId, isEqualTo(commentId)));
    }

    @Override
    public void insertUserLikeComment(UserLikeComment userLikeComment) {
        userLikeCommentMapper.insert(userLikeComment);
    }

    @Override
    public void insertUserCommentRel(UserCommentRel userCommentRel) {
        userCommentRelMapper.insert(userCommentRel);
    }

}
