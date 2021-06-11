package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.dao.PostAuthMapper;
import com.edu.nju.alley.dao.PostMapper;
import com.edu.nju.alley.dao.support.PostDSS;
import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.enums.Label;
import com.edu.nju.alley.enums.Msg;
import com.edu.nju.alley.enums.Sort;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import com.edu.nju.alley.po.*;
import com.edu.nju.alley.service.CommentService;
import com.edu.nju.alley.service.PostCommentService;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.service.UserPostService;
import com.edu.nju.alley.vo.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.select;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    private final PostAuthMapper postAuthMapper;

    private final CommentService commentService;

    private final PostCommentService postCommentService;

    private final UserPostService userPostService;

    @Autowired
    public PostServiceImpl(PostMapper postMapper,
                           PostAuthMapper postAuthMapper,
                           CommentService commentService,
                           PostCommentService postCommentService,
                           UserPostService userPostService) {
        this.postMapper = postMapper;
        this.postAuthMapper = postAuthMapper;
        this.commentService = commentService;
        this.postCommentService = postCommentService;
        this.userPostService = userPostService;
    }

    @Override
    public PostVO getSpecificPost(Integer postId) {
        Post post = this.getSherPost(postId);
        if (post == null) throw new NoSuchDataException(Msg.NoSuchPostError.getMsg());
        //找到权限
        PostAuth postAuth = this.getSherPostAuth(post.getAuthId());
        if (postAuth == null) throw new NoSuchDataException(Msg.NoSuchAuthError.getMsg());

        return new PostVO(post, this.getPostComments(postId), new PostAuthVO(postAuth));
    }

    @Override
    public void updatePost(Integer postId, PostDTO postDTO) {
        // 将请求封装成post
        Post post = this.getSherPost(postId);
        if (post == null) throw new NoSuchDataException(Msg.NoSuchPostError.getMsg());
        // 找到权限
        PostAuth postAuth = this.getSherPostAuth(post.getAuthId());
        if (postAuth == null) throw new NoSuchDataException(Msg.NoSuchAuthError.getMsg());

        post.updateByDTO(postDTO);
        postAuth.updateByDTO(postDTO.getAuth());
    }

    @Override
    public LikeVO likePost(Integer postId, Integer userId) {
        UserLikePost userLikePost = userPostService.getSherUserLikePost(userId, postId);
        Post post = this.getSherPost(postId);
        if (post == null) throw new NoSuchDataException(Msg.NoSuchPostError.getMsg());

        // 已经点过赞 取消点赞
        if (userLikePost != null) {
            // 点赞数减一
            post.setLikeNum(post.getLikeNum() - 1);
            userPostService.deleteUserLikePost(userId, postId);
            postMapper.updateByPrimaryKeySelective(post);
            return new LikeVO(false);
        }
        // 点赞
        userLikePost = new UserLikePost(userId, postId);
        // 点赞数加一
        post.setLikeNum(post.getLikeNum() + 1);
        userPostService.insertUserLikePost(userLikePost);
        postMapper.updateByPrimaryKeySelective(post);

        return new LikeVO(true);
    }

    @Override
    public CommentVO commentPost(CommentDTO commentDTO) {
        // 评论数加一
        Post post = this.getSherPost(commentDTO.getPostId());
        if (post == null) throw new NoSuchDataException(Msg.NoSuchPostError.getMsg());

        post.setCommentNum(post.getCommentNum() + 1);
        postMapper.updateByPrimaryKeySelective(post);

        Comment comment = Comment.PostComment(commentDTO);
        commentService.insertOne(comment);

        PostCommentRel postCommentRel = new PostCommentRel(commentDTO.getPostId(), comment.getId());
        postCommentService.insertPostCommentRel(postCommentRel);

        return new CommentVO(comment, commentDTO.getUserId(), null);
    }

    @Override
    public NewRecordVO createPost(PostDTO postDTO) {
        Post post = new Post(postDTO);
        // 创建postAuth并获得id
        PostAuth postAuth = new PostAuth(postDTO.getAuth());
        postAuthMapper.insert(postAuth);

        post.setAuthId(postAuth.getId());
        postMapper.insert(post);

        UserPostRel userPostRel = new UserPostRel(post.getUserId(), post.getId());
        userPostService.insertUserPostRel(userPostRel);
        return new NewRecordVO(post.getId());
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.getSherPost(postId);
        if (post == null) throw new NoSuchDataException(Msg.NoSuchPostError.getMsg());
        // 删除post本身
        postMapper.deleteByPrimaryKey(postId);
        // 删除post权限
        postAuthMapper.deleteByPrimaryKey(post.getAuthId());
        // 删除所有相关comment
        postCommentService.getSherPostCommentRel(postId)
                .forEach(u -> commentService.deleteComment(u.getCommentId()));
        // 删除post和comment关系记录
        postCommentService.deletePostCommentRelByPost(postId);
        // 删除user和post点赞记录
        userPostService.deleteUserLikePostByPost(postId);
        // 删除user和post关系记录
        userPostService.deleteUserPostRelByPost(postId);
    }

    @Override
    public List<PostViewVO> getAllPostView(Integer sort, Integer label, Integer userId) {
        return this.getAllSortedPosts(sort)
                .stream()
                .filter(c -> (c.getLabelId().equals(label)
                        || label == Label.ALL.getCode()
                        || (label == Label.SELF.getCode() && c.getUserId().equals(userId))))
                .sorted(Comparator.comparing(Post::getCreateT))
                .map(t -> new PostViewVO(t.getId(), t.getLatitude(), t.getLongitude()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentVO> getPostComments(Integer postId) {
        return postCommentService.getSherPostCommentRel(postId).stream()
                .map(t -> commentService.getSpecificComment(t.getCommentId()))
                .sorted(Comparator.comparing(CommentVO::getCreateTime))
                .collect(Collectors.toList());
    }

    // 工具方法，负责返回各种PO
    // 工具方法不抛异常，具体由调用函数自己决定
    @Override
    public Post getSherPost(Integer postId) {
        Optional<Post> postOptional = postMapper.selectByPrimaryKey(postId);
        if (postOptional.isEmpty()) return null;
        return postOptional.get();
    }

    @Override
    public PostAuth getSherPostAuth(Integer postAuthId) {
        Optional<PostAuth> postAuthOptional = postAuthMapper.selectByPrimaryKey(postAuthId);
        if (postAuthOptional.isEmpty()) return null;
        return postAuthOptional.get();
    }

    @Override
    public List<Post> getAllSortedPosts(Integer sort) {
        // bug： 浮点数精度不高
        SelectStatementProvider selectAll = select(PostMapper.selectList)
                .from(PostDSS.post)
                .orderBy((sort == Sort.HOT.getCode()) ? PostDSS.likeNum.descending() : PostDSS.lastModifiedT.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return postMapper.selectMany(selectAll)
                .stream()
                .sorted(Comparator.comparing(Post::getCreateT))
                .collect(Collectors.toList());
    }

}
