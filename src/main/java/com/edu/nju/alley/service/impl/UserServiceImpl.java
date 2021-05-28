package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.constant.Constant;
import com.edu.nju.alley.dao.*;
import com.edu.nju.alley.dao.support.*;
import com.edu.nju.alley.dto.UserAuthDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.po.*;
import com.edu.nju.alley.service.UserService;
import com.edu.nju.alley.vo.*;
import com.github.pagehelper.PageHelper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Resource
    PostMapper postMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    UserLikeCommentMapper userLikeCommentMapper;

    @Resource
    UserLikePostMapper userLikePostMapper;

    @Resource
    UserPostRelMapper userPostRelMapper;

    @Resource
    PostServiceImpl postService;

    @Resource
    CommentServiceImpl commentService;

    @Resource
    UserCommentRelMapper userCommentRelMapper;

    @Resource
    UserAuthMapper userAuthMapper;

    @Override
    public ResponseVO getUserPost(Integer userId, Integer pageId) {
        //返回用户所有帖子
        PageHelper.startPage(pageId, Constant.pageSize);
        ResponseVO res = ResponseVO.success();
        List<UserPostRel> userPostRels = userPostRelMapper.select(c -> c.where(UserPostRelDSS.userId, SqlBuilder.isEqualTo(userId)));
        List<PostVO> postVOS = new ArrayList<PostVO>();
        for (UserPostRel userPostRel : userPostRels) {
            postVOS.add((PostVO) postService.getSpecialPost(userPostRel.getPostId()).getData());
        }
        res.add(postVOS);
        return res;
    }

    @Override
    public ResponseVO getUserComment(Integer userId, Integer pageId) {
        //返回用户所有评论
        PageHelper.startPage(pageId, Constant.pageSize);
        ResponseVO res = ResponseVO.success();
        List<UserCommentRel> userCommentRels = userCommentRelMapper.select(c -> c.where(UserCommentRelDSS.userId, SqlBuilder.isEqualTo(userId)));
        List<CommentVO> commentVOS = new ArrayList<CommentVO>();
        for (UserCommentRel userCommentRel : userCommentRels) {
            commentVOS.add(commentService.getComment(userCommentRel.getCommentId()));
        }
        res.add(commentVOS);
        return res;
    }

    @Override
    public ResponseVO getUserLike(Integer userId, Integer pageId) {
        PageHelper.startPage(pageId, Constant.pageSize);
        //是返回点赞的帖子还是评论？
        List<LikeVO> likeVOS = new ArrayList<LikeVO>();
        List<UserLikeComment> userLikeComments = userLikeCommentMapper.select(c -> c.where(UserLikeCommentDSS.userId, SqlBuilder.isEqualTo(userId)));
        for (UserLikeComment userLikeComment : userLikeComments) {
            likeVOS.add(new LikeVO(userLikeComment));
        }
        List<UserLikePost> userLikePosts = userLikePostMapper.select(c -> c.where(UserLikePostDSS.userId, SqlBuilder.isEqualTo(userId)));
        for (UserLikePost userLikePost : userLikePosts) {
            likeVOS.add(new LikeVO(userLikePost));
        }
        ResponseVO res = ResponseVO.success();
        res.add(likeVOS);
        return res;
    }

    @Override
    public ResponseVO viewUser(Integer userId) {
        //查看用户信息
        //得到用户基本信息
        Optional<User> user = userMapper.selectOne(c -> c
                .where(UserDSS.id, SqlBuilder.isEqualTo(userId)));
        //得到用户所有帖子
        List<UserPostRel> userPostRels = userPostRelMapper.select(
                c -> c.where(UserPostRelDSS.userId, SqlBuilder.isEqualTo(userId)));
        List<PostVO> postVOS = new ArrayList<>();
        for (UserPostRel userPostRel : userPostRels) {
            postVOS.add((PostVO) postService.getSpecialPost(userPostRel.getPostId()).getData());
        }
        //得到用户权限数据
        Optional<UserAuth> userAuth = userAuthMapper.selectByPrimaryKey(userId);
        return ResponseVO.success().add(new UserVO(user.get(), postVOS, userAuth.get()));
    }

    @Override
    public ResponseVO updateUser(Integer userId, UserDTO userDTO) {
        Optional<User> user = userMapper.selectOne(c -> c.where(UserDSS.id, SqlBuilder.isEqualTo(userId)));
        User NewUser = user.get();
        updateUser(NewUser, userDTO);

        return ResponseVO.success();
    }

    public void updateUser(User user, UserDTO userDTO) {
        user.setSign(userDTO.getSign());
        Optional<UserAuth> userAuth = userAuthMapper.selectByPrimaryKey(user.getAuthId());
        updateUserAuth(userAuth.get(), userDTO.getAuth());
    }

    public void updateUserAuth(UserAuth userAuth, UserAuthDTO userAuthDTO) {
        userAuth.setChat(userAuthDTO.isChat());
        userAuth.setMakeFriend(userAuthDTO.isMkfriend());
        userAuth.setOfficial(userAuthDTO.isOfficial());
        userAuth.setShowWxInfo(userAuthDTO.isWxInfo());
        userAuth.setPosition(userAuthDTO.isLocate());
    }
}
