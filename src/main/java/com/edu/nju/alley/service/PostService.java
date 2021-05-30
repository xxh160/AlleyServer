package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.vo.LikeVO;
import com.edu.nju.alley.vo.NewRecordVO;
import com.edu.nju.alley.vo.PostVO;
import com.edu.nju.alley.vo.PostViewVO;

import java.util.List;


public interface PostService {

    PostVO getSpecificPost(Integer postId);

    void updatePost(Integer postId, PostDTO postDTO);

//    void updatePostPicture();

    LikeVO likePost(Integer postId, Integer likerId);

    NewRecordVO commentPost(CommentDTO commentDTO);

    NewRecordVO createPost(PostDTO postDTO);

    void deletePost(Integer postId);

    List<PostViewVO> getAllPostView(Integer sort, Integer label);

    List<Post> getAllSortedPosts(Integer sort);

}
