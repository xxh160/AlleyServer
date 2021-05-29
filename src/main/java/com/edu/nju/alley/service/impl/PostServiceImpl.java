package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.*;
import com.edu.nju.alley.dao.support.PostCommentRelDSS;
import com.edu.nju.alley.dao.support.PostDSS;
import com.edu.nju.alley.dao.support.UserLikePostDSS;
import com.edu.nju.alley.dao.support.UserPostRelDSS;
import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.enums.LabelSelectType;
import com.edu.nju.alley.enums.SortType;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import com.edu.nju.alley.po.*;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.vo.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    private final PostAuthMapper postAuthMapper;

    private final PostCommentRelMapper postCommentRelMapper;

    private final UserLikePostMapper userLikePostMapper;

    private final UserPostRelMapper userPostRelMapper;

    private final CommentService commentService;

    @Autowired
    public PostServiceImpl(PostMapper postMapper,
                           PostAuthMapper postAuthMapper,
                           PostCommentRelMapper postCommentRelMapper,
                           UserLikePostMapper userLikePostMapper,
                           UserPostRelMapper userPostRelMapper,
                           CommentService commentService) {
        this.postMapper = postMapper;
        this.postAuthMapper = postAuthMapper;
        this.postCommentRelMapper = postCommentRelMapper;
        this.userLikePostMapper = userLikePostMapper;
        this.userPostRelMapper = userPostRelMapper;
        this.commentService = commentService;
    }

    @Override
    public PostVO getSpecificPost(Integer postId) {
        Optional<Post> postOptional = postMapper.selectByPrimaryKey(postId);
        if (!postOptional.isPresent()) throw new NoSuchDataException("没有这条帖子");
        Post post = postOptional.get();
        List<PostCommentRel> postCommentRels = postCommentRelMapper
                .select(c -> c.where(PostCommentRelDSS.postId, isEqualTo(post.getId())));
        List<CommentVO> commentVOList = postCommentRels.stream()
                .map(t -> commentService.getSpecificOne(t.getCommentId()))
                .collect(Collectors.toList());
        //找到权限
        Optional<PostAuth> postAuthOptional = postAuthMapper.selectByPrimaryKey(post.getId());
        if (!postAuthOptional.isPresent()) throw new NoSuchDataException("帖子没有对应的权限");
        return new PostVO(post, commentVOList, new PostAuthVO(postAuthOptional.get()));
    }

    @Override
    public void updatePost(Integer postId, PostDTO postDTO) {
        //将请求封装成post
        Optional<Post> postOptional = postMapper.selectByPrimaryKey(postId);
        if (!postOptional.isPresent()) throw new NoSuchDataException("没有这条帖子");
        Post post = postOptional.get();

        Optional<PostAuth> postAuthOptional = postAuthMapper.selectByPrimaryKey(post.getAuthId());
        if (!postAuthOptional.isPresent()) throw new NoSuchDataException("帖子没有对应的权限");
        PostAuth postAuth = postAuthOptional.get();

        post.updateByDTO(postDTO);
        postAuth.updateByDTO(postDTO.getAuth());
    }

    @Override
    public LikeVO likePost(Integer postId, Integer likerId) {
        Optional<UserLikePost> userLikePostOptional = userLikePostMapper
                .selectOne(c -> c.where(UserLikePostDSS.postId, isEqualTo(postId))
                        .and(UserLikePostDSS.userId, isEqualTo(likerId)));

        Optional<Post> postOptional = postMapper
                .selectOne(c -> c.where(PostDSS.id, isEqualTo(postId)));
        if (!postOptional.isPresent()) throw new NoSuchDataException("没有这条帖子");
        Post post = postOptional.get();
        // 已经点过赞 取消点赞
        if (userLikePostOptional.isPresent()) {
            // 点赞数减一
            post.setLikeNum(post.getLikeNum() - 1);
            userLikePostMapper.delete(c -> c.where(UserLikePostDSS.postId, isEqualTo(postId))
                    .and(UserLikePostDSS.userId, isEqualTo(likerId)));
            postMapper.updateByPrimaryKeySelective(post);
            return new LikeVO(false);
        }
        // 点赞
        UserLikePost userLikePost = new UserLikePost(likerId, postId);
        // 点赞数加一
        post.setLikeNum(post.getLikeNum() + 1);
        userLikePostMapper.insert(userLikePost);
        postMapper.updateByPrimaryKeySelective(post);

        return new LikeVO(true);
    }

    @Override
    public NewRecordVO commentPost(CommentDTO commentDTO) {
        Comment comment = Comment.PostComment(commentDTO);
        commentService.insertOne(comment);
        PostCommentRel postCommentRel = new PostCommentRel(commentDTO.getPostId(), comment.getId());
        postCommentRelMapper.insert(postCommentRel);
        return new NewRecordVO(comment.getId());
    }

    @Override
    public NewRecordVO createPost(PostDTO postDTO) {
        Post post = new Post(postDTO);
        // 创建postAuth并获得id
        PostAuth postAuth = new PostAuth(post.getId(), postDTO.getAuth());
        postAuthMapper.insert(postAuth);
        post.setAuthId(postAuth.getId());
        postMapper.insert(post);
        UserPostRel userPostRel = new UserPostRel(post.getUserId(), post.getId());
        userPostRelMapper.insert(userPostRel);
        return new NewRecordVO(post.getId());
    }

    @Override
    public void deletePost(Integer postId) {
        Optional<Post> postOptional = postMapper.selectByPrimaryKey(postId);
        if (!postOptional.isPresent()) throw new NoSuchDataException("没有这条帖子");
        Post post = postOptional.get();
        postMapper.deleteByPrimaryKey(postId);
        postAuthMapper.deleteByPrimaryKey(post.getAuthId());
        postCommentRelMapper.delete(c -> c.where(PostCommentRelDSS.postId, isEqualTo(postId)));
        userLikePostMapper.delete(c -> c.where(UserLikePostDSS.postId, isEqualTo(postId)));
        userPostRelMapper.delete(c -> c.where(UserPostRelDSS.postId, isEqualTo(postId)));
    }

    @Override
    public List<PostViewVO> getAllPostView(Integer sort, Integer label) {
        return this.getAllSortedPosts(sort)
                .stream()
                .filter(c -> (c.getLabelId().equals(label) || label == LabelSelectType.ALL.getCode()))
                .map(t -> new PostViewVO(t.getId(), t.getLatitude(), t.getLongitude()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllSortedPosts(Integer sort) {
        SelectStatementProvider selectAll = select(PostMapper.selectList)
                .from(PostDSS.post)
                .orderBy((sort == SortType.HOT.getCode()) ? PostDSS.likeNum : PostDSS.lastModifiedT)
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return postMapper.selectMany(selectAll);
    }

}
