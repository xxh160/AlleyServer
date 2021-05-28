package com.edu.nju.alley.service.impl;

import cn.hutool.core.date.DateUtil;
import com.edu.nju.alley.dao.*;
import com.edu.nju.alley.dao.support.PostCommentRelDSS;
import com.edu.nju.alley.dao.support.PostDSS;
import com.edu.nju.alley.dao.support.UserLikePostDSS;
import com.edu.nju.alley.dao.support.UserPostRelDSS;
import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostAuthDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.po.*;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.PostLikeVO;
import com.edu.nju.alley.vo.PostVO;
import com.edu.nju.alley.vo.ResponseVO;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Resource
    PostMapper postMapper;

    @Resource
    UserLikeCommentMapper userLikeCommentMapper;

    @Resource
    PostAuthMapper postAuthMapper;

    @Resource
    PostCommentRelMapper postCommentRelMapper;

    @Resource
    UserCommentRelMapper userCommentRelMapper;

    @Resource
    UserLikePostMapper userLikePostMapper;

    @Resource
    UserPostRelMapper userPostRelMapper;

    @Resource
    CommentMapper commentMapper;

    @Resource
    CommentServiceImpl commentService;

    @Override
    public ResponseVO getSpecialPost(Integer postId) {
        Optional<Post> post = postMapper.selectByPrimaryKey(postId);
        List<PostCommentRel> postCommentRels = postCommentRelMapper.select(c -> c.where(PostCommentRelDSS.postId, SqlBuilder.isEqualTo(post.get().getId())));
        List<CommentVO> commentVOS = new ArrayList<CommentVO>();
        for (PostCommentRel postCommentRel : postCommentRels) {
            commentVOS.add(commentService.getComment(postCommentRel.getCommentId()));
        }
        //找到权限
        Optional<PostAuth> postAuth = postAuthMapper.selectByPrimaryKey(post.get().getId());
        ResponseVO ret = ResponseVO.success();
        ret.add(new PostVO(post.get(), commentVOS, postAuth.get()));
        return ret;
    }

    @Override
    public ResponseVO updatePost(Integer postId, PostDTO postDTO) {
        //将请求封装成post
        Optional<Post> post = postMapper.selectByPrimaryKey(postId);
        Post NewPost = post.get();
        updatePost(NewPost, postDTO);

        return ResponseVO.success();
    }

    @Override
    public ResponseVO likePost(Integer postId, Integer likerId) {
        Optional<UserLikePost> userLikePost = userLikePostMapper.selectOne(c -> c.where(UserLikePostDSS.postId, SqlBuilder.isEqualTo(postId)).and(UserLikePostDSS.userId, SqlBuilder.isEqualTo(likerId)));
        Post post;
        if (userLikePost.isPresent()) {
            post = postMapper.selectOne(c -> c.where(PostDSS.id, SqlBuilder.isEqualTo(postId))).get();
            post.setLikeNum(post.getLikeNum() - 1);//点赞数减一
            userLikePostMapper.delete(c -> c.where(UserLikePostDSS.postId, SqlBuilder.isEqualTo(postId)).and(UserLikePostDSS.userId, SqlBuilder.isEqualTo(likerId)));
        } else {
            UserLikePost userLikePost1 = new UserLikePost(postId, likerId);
            post = postMapper.selectOne(c -> c.where(PostDSS.id, SqlBuilder.isEqualTo(postId))).get();
            post.setLikeNum(post.getLikeNum() + 1);//点赞数加一
            userLikePostMapper.insert(userLikePost1);
        }
        ResponseVO res = ResponseVO.success();
        PostLikeVO postLikeVO = new PostLikeVO(post.getLikeNum());
        res.add(postLikeVO);
        return res;
    }

    @Override
    public ResponseVO commentPost(CommentDTO commentDTO) {
        Comment comment = new Comment(commentDTO, 1);//1代表Post
        commentMapper.insert(comment);
        PostCommentRel postCommentRel = new PostCommentRel(commentDTO.getPostId(), comment.getId());
        postCommentRelMapper.insert(postCommentRel);
        UserCommentRel userCommentRel = new UserCommentRel(commentDTO.getUserId(), comment.getId());
        userCommentRelMapper.insert(userCommentRel);

        return ResponseVO.success();
    }

    @Override
    public ResponseVO createPost(PostDTO postDTO) {
        Post post = new Post(postDTO);
        postMapper.insert(post);//插入一个post
        PostAuth postAuth = new PostAuth(post.getId(), postDTO.getAuth());
        postAuthMapper.insert(postAuth);
        return ResponseVO.success();
    }

    @Override
    public ResponseVO deletePost(Integer postId) {
        postMapper.deleteByPrimaryKey(postId);
        postAuthMapper.deleteByPrimaryKey(postId);
        postCommentRelMapper.delete(c -> c.where(PostCommentRelDSS.postId, SqlBuilder.isEqualTo(postId)));
        userLikePostMapper.delete(c -> c.where(UserLikePostDSS.postId, SqlBuilder.isEqualTo(postId)));
        userPostRelMapper.delete(c -> c.where(UserPostRelDSS.postId, SqlBuilder.isEqualTo(postId)));
        return ResponseVO.success();
    }

    public void updatePost(Post post, PostDTO postDTO) {
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAnchorId(postDTO.getAnchorId());
        post.setLastModifiedT(DateUtil.date());
        post.setAddrX(post.getAddrX());
        post.setAddrY(post.getAddrY());
        Optional<PostAuth> postAuth = postAuthMapper.selectByPrimaryKey(post.getAuthId());
        updatePostAuth(postAuth.get(), postDTO.getAuth());
    }

    public void updatePostAuth(PostAuth postAuth, PostAuthDTO postAuthDTO) {
        postAuth.setVisible(postAuthDTO.isVisible());
        postAuth.setComment(postAuthDTO.isComment());
    }
}
