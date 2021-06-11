package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.PostCommentRelMapper;
import com.edu.nju.alley.dao.support.PostCommentRelDSS;
import com.edu.nju.alley.po.PostCommentRel;
import com.edu.nju.alley.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRelMapper postCommentRelMapper;

    @Autowired
    public PostCommentServiceImpl(PostCommentRelMapper postCommentRelMapper) {
        this.postCommentRelMapper = postCommentRelMapper;
    }

    @Override
    public List<PostCommentRel> getSherPostCommentRel(Integer postId) {
        return postCommentRelMapper.select(c -> c.where(PostCommentRelDSS.postId, isEqualTo(postId)));
    }

    @Override
    public void deletePostCommentRelByPost(Integer postId) {
        postCommentRelMapper.delete(c -> c.where(PostCommentRelDSS.postId, isEqualTo(postId)));
    }

    @Override
    public void insertPostCommentRel(PostCommentRel postCommentRel) {
        postCommentRelMapper.insert(postCommentRel);
    }

    @Override
    public PostCommentRel getSherPostCommentRelByComment(Integer commentId) {
        Optional<PostCommentRel> postCommentRelOptional = postCommentRelMapper
                .selectOne(c -> c.where(PostCommentRelDSS.commentId, isEqualTo(commentId)));
        return postCommentRelOptional.orElse(null);
    }

}
