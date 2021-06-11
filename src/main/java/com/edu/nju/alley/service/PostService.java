package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.po.PostAuth;
import com.edu.nju.alley.vo.*;

import java.util.List;


public interface PostService {

    PostVO getSpecificPost(Integer postId);

    void updatePost(Integer postId, PostDTO postDTO);

    LikeVO likePost(Integer postId, Integer likerId);

    CommentVO commentPost(CommentDTO commentDTO);

    NewRecordVO createPost(PostDTO postDTO);

    void deletePost(Integer postId);

    List<PostViewVO> getAllPostView(Integer sort, Integer label, Integer userId);

    List<CommentVO> getPostComments(Integer postId);

    List<Post> getAllSortedPosts(Integer sort);


    Post getSherPost(Integer postId);

    PostAuth getSherPostAuth(Integer postAuthId);

}
