package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.CommentMapper;
import com.edu.nju.alley.dao.CommentRelMapper;
import com.edu.nju.alley.dao.UserCommentRelMapper;
import com.edu.nju.alley.dao.UserLikeCommentMapper;
import com.edu.nju.alley.dao.support.CommentRelDSS;
import com.edu.nju.alley.dao.support.UserCommentRelDSS;
import com.edu.nju.alley.dao.support.UserLikeCommentDSS;
import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import com.edu.nju.alley.po.Comment;
import com.edu.nju.alley.po.CommentRel;
import com.edu.nju.alley.po.UserCommentRel;
import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.LikeVO;
import com.edu.nju.alley.vo.NewRecordVO;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final CommentRelMapper commentRelMapper;

    private final UserCommentRelMapper userCommentRelMapper;

    private final UserLikeCommentMapper userLikeCommentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper,
                              CommentRelMapper commentRelMapper,
                              UserCommentRelMapper userCommentRelMapper,
                              UserLikeCommentMapper userLikeCommentMapper) {
        this.commentMapper = commentMapper;
        this.commentRelMapper = commentRelMapper;
        this.userCommentRelMapper = userCommentRelMapper;
        this.userLikeCommentMapper = userLikeCommentMapper;
    }

    @Override
    public NewRecordVO commentComment(CommentDTO commentDTO) {
        Comment comment = Comment.AdvancedComment(commentDTO);
        // 插入comment 更新user_comment_rel数据库
        this.insertOne(comment);
        // 更新comment_rel数据库
        // insert后数据库自动更新po中的id主键
        CommentRel commentRel = new CommentRel(commentDTO.getFatherId(), comment.getId());
        commentRelMapper.insert(commentRel);
        return new NewRecordVO(comment.getId());
    }

    @Override
    public LikeVO likeComment(Integer commentId, Integer likerId) {
        // 查看数据库中有没有点赞记录
        Optional<UserLikeComment> userLikeCommentOptional = userLikeCommentMapper
                .selectOne(c -> c.where(UserLikeCommentDSS.commentId, isEqualTo(commentId))
                        .and(UserLikeCommentDSS.userId, SqlBuilder.isEqualTo(likerId)));
        // 把评论找出来
        Optional<Comment> commentOptional = commentMapper.selectByPrimaryKey(commentId);
        if (commentOptional.isEmpty()) throw new NoSuchDataException("没有这条评论");
        Comment comment = commentOptional.get();
        // 有点赞记录 则取消点赞
        if (userLikeCommentOptional.isPresent()) {
            comment.setLikeNum(comment.getLikeNum() - 1);
            // 好像用不用selective没什么区别
            commentMapper.updateByPrimaryKeySelective(comment);
            // 删除点赞记录
            userLikeCommentMapper.delete(c -> c.where(UserLikeCommentDSS.commentId, isEqualTo(commentId))
                    .and(UserLikeCommentDSS.userId, isEqualTo(likerId)));
            return new LikeVO(false);
        }
        // 没有点赞记录 则点赞
        UserLikeComment userLikeComment = new UserLikeComment(likerId, commentId);
        userLikeCommentMapper.insert(userLikeComment);
        comment.setLikeNum(comment.getLikeNum() + 1);
        commentMapper.updateByPrimaryKeySelective(comment);
        return new LikeVO(true);
    }

    @Override
    public CommentVO getSpecificComment(Integer commentId) {
        Optional<Comment> commentOptional = commentMapper.selectByPrimaryKey(commentId);
        if (commentOptional.isEmpty()) return null;
        Comment comment = commentOptional.get();
        // 寻找子评论id
        // 如果为空 返回的是长度为0的ArrayList
        List<CommentRel> commentRelList = commentRelMapper
                .select(c -> c.where(CommentRelDSS.fatherId, isEqualTo(commentId)));

        List<CommentVO> children = commentRelList.stream()
                .map(t -> getSpecificComment(t.getChildId())).collect(Collectors.toList());

        Optional<UserCommentRel> userCommentRelOptional = userCommentRelMapper
                .selectOne(c -> c.where(UserCommentRelDSS.commentId, isEqualTo(commentId)));
        if (userCommentRelOptional.isEmpty()) throw new NoSuchDataException("评论没有作者");

        return new CommentVO(comment, userCommentRelOptional.get().getUserId(), children);
    }

    @Override
    public void insertOne(Comment comment) {
        // 将一条评论插入数据库，并更新user_comment_rel，其余不是它的职责
        commentMapper.insert(comment);
        UserCommentRel userCommentRel = new UserCommentRel(comment.getUserId(), comment.getId());
        userCommentRelMapper.insert(userCommentRel);
    }

    @Override
    public List<Comment> getChildComments(Integer commentId) {
        return commentRelMapper
                .select(c -> c.where(CommentRelDSS.fatherId, isEqualTo(commentId)))
                .stream()
                .map(t -> getSherComment(t.getChildId())).collect(Collectors.toList());
    }

    public Comment getSherComment(Integer commentId) {
        Optional<Comment> commentOptional = commentMapper.selectByPrimaryKey(commentId);
        if (commentOptional.isEmpty()) return null;
        return commentOptional.get();
    }

}
