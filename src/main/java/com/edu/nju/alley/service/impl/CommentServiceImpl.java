package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.*;
import com.edu.nju.alley.dao.support.CommentRelDSS;
import com.edu.nju.alley.dao.support.PostCommentRelDSS;
import com.edu.nju.alley.dao.support.UserLikeCommentDSS;
import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.po.*;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.vo.CommentLikeVO;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.ResponseVO;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;

    @Resource
    CommentRelMapper commentRelMapper;

    @Resource
    PostCommentRelMapper postCommentRelMapper;

    @Resource
    UserCommentRelMapper userCommentRelMapper;

    @Resource
    UserLikeCommentMapper userLikeCommentMapper;

    @Override
    public ResponseVO commentComment(CommentDTO commentDTO) {
        Comment comment = new Comment(commentDTO, 1);//2代表Comment
        commentMapper.insert(comment);
        PostCommentRel postCommentRel = new PostCommentRel(commentDTO.getPostId(), comment.getId());
        postCommentRelMapper.insert(postCommentRel);
        //CommentRel commentRel=new CommentRel();
        UserCommentRel userCommentRel = new UserCommentRel(commentDTO.getUserId(), comment.getId());
        userCommentRelMapper.insert(userCommentRel);

        return ResponseVO.success();
    }

    @Override
    public ResponseVO likeComment(Integer commentId, Integer likerId) {
        Optional<UserLikeComment> userLikeComment = userLikeCommentMapper.selectOne(c -> c.where(UserLikeCommentDSS.commentId, SqlBuilder.isEqualTo(commentId)).and(UserLikeCommentDSS.userId, SqlBuilder.isEqualTo(likerId)));
        Comment comment;
        if (userLikeComment.isPresent()) {
            comment = commentMapper.selectByPrimaryKey(commentId).get();
            comment.setLikeNum(comment.getLikeNum() - 1);
            userLikeCommentMapper.delete(c -> c.where(UserLikeCommentDSS.commentId, SqlBuilder.isEqualTo(commentId)).and(UserLikeCommentDSS.userId, SqlBuilder.isEqualTo(likerId)));
        } else {
            UserLikeComment userLikeComment1 = new UserLikeComment(likerId, commentId);
            comment = commentMapper.selectByPrimaryKey(commentId).get();
            comment.setLikeNum(comment.getLikeNum() + 1);
            userLikeCommentMapper.insert(userLikeComment1);
        }
        ResponseVO res = ResponseVO.success();
        CommentLikeVO commentLikeVO = new CommentLikeVO(comment.getLikeNum());
        res.add(commentLikeVO);
        return res;
    }

    @Override
    public CommentVO getComment(Integer commentId) {
        Optional<Comment> comment = commentMapper.selectByPrimaryKey(commentId);
        List<CommentRel> commentRelList = commentRelMapper.select(c -> c.where(CommentRelDSS.fatherId, SqlBuilder.isEqualTo(commentId)));//寻找有没有子评论
        List<CommentVO> commentVOS = new ArrayList<CommentVO>();//子评论集合
        Optional<PostCommentRel> postCommentRel = postCommentRelMapper.selectOne(c -> c.where(PostCommentRelDSS.commentId, SqlBuilder.isEqualTo(commentId)));
        Integer postId = postCommentRel.get().getPostId();
        if (commentRelList != null) {//如果有子评论
            //将所有子评论加入数组中
            for (CommentRel commentRel : commentRelList) {
                commentVOS.add(getComment(commentRel.getChildId()));
            }
        }
        return new CommentVO(comment.get(), commentVOS, postId);
    }

}
