package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.CommentMapper;
import com.edu.nju.alley.dao.CommentRelMapper;
import com.edu.nju.alley.dao.support.CommentRelDSS;
import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.enums.Msg;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import com.edu.nju.alley.po.Comment;
import com.edu.nju.alley.po.CommentRel;
import com.edu.nju.alley.po.UserCommentRel;
import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.service.UserCommentService;
import com.edu.nju.alley.service.UserPostService;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.LikeVO;
import com.edu.nju.alley.vo.NewRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    private final CommentRelMapper commentRelMapper;

    private final UserCommentService userCommentService;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper,
                              CommentRelMapper commentRelMapper,
                              UserCommentService userCommentService,
                              UserPostService userPostService) {
        this.commentMapper = commentMapper;
        this.commentRelMapper = commentRelMapper;
        this.userCommentService = userCommentService;
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
    public LikeVO likeComment(Integer commentId, Integer userId) {
        // 查看数据库中有没有点赞记录
        UserLikeComment userLikeComment = userCommentService.getSherUserLikeComment(userId, commentId);
        // 把评论找出来
        Comment comment = this.getSherComment(commentId);
        if (comment == null) throw new NoSuchDataException(Msg.NoSuchCommentError.getMsg());

        // 有点赞记录 则取消点赞
        if (userLikeComment != null) {
            comment.setLikeNum(comment.getLikeNum() - 1);
            // 好像用不用selective没什么区别
            commentMapper.updateByPrimaryKeySelective(comment);
            // 删除点赞记录
            userCommentService.deleteUserLikeComment(userId, commentId);
            return new LikeVO(false);
        }
        // 没有点赞记录 则点赞
        userLikeComment = new UserLikeComment(userId, commentId);
        userCommentService.insertUserLikeComment(userLikeComment);
        comment.setLikeNum(comment.getLikeNum() + 1);
        commentMapper.updateByPrimaryKeySelective(comment);
        return new LikeVO(true);
    }

    @Override
    public CommentVO getSpecificComment(Integer commentId) {
        Comment comment = this.getSherComment(commentId);
        if (comment == null) return null;
        // 寻找子评论id
        // 如果为空 返回的是长度为0的ArrayList
        List<CommentRel> commentRelList = commentRelMapper
                .select(c -> c.where(CommentRelDSS.fatherId, isEqualTo(commentId)));

        List<CommentVO> children = commentRelList.stream()
                .map(t -> getSpecificComment(t.getChildId())).collect(Collectors.toList());

        // 寻找评论作者
        UserCommentRel userCommentRel = userCommentService.getSherUserCommentRelByComment(commentId);
        if (userCommentRel == null) throw new NoSuchDataException(Msg.NoRelError.getMsg());

        return new CommentVO(comment, userCommentRel.getUserId(), children);
    }

    @Override
    public void insertOne(Comment comment) {
        // 将一条评论插入数据库，并更新user_comment_rel，其余不是它的职责
        commentMapper.insert(comment);
        UserCommentRel userCommentRel = new UserCommentRel(comment.getUserId(), comment.getId());
        userCommentService.insertUserCommentRel(userCommentRel);
    }

    @Override
    public void deleteComment(Integer commentId) {
        // 删除评论本身
        commentMapper.deleteByPrimaryKey(commentId);
        // 删除user和comment关系记录
        userCommentService.deleteUserCommentRelByComment(commentId);
        // 删除user和comment点赞记录
        userCommentService.deleteUserLikeCommentByComment(commentId);

    }

    // 工具方法，负责返回各种PO
    // 工具方法不抛异常，具体由调用函数自己决定
    @Override
    public Comment getSherComment(Integer commentId) {
        Optional<Comment> commentOptional = commentMapper.selectByPrimaryKey(commentId);
        if (commentOptional.isEmpty()) return null;
        return commentOptional.get();
    }

    @Override
    public List<Comment> getChildComments(Integer commentId) {
        return commentRelMapper
                .select(c -> c.where(CommentRelDSS.fatherId, isEqualTo(commentId)))
                .stream()
                .map(t -> getSherComment(t.getChildId()))
                .sorted(Comparator.comparing(Comment::getCreateT))
                .collect(Collectors.toList());
    }

}
