package com.edu.nju.alley.service;

import com.edu.nju.alley.po.PostCommentRel;

import java.util.List;

public interface PostCommentService {

    List<PostCommentRel> getSherPostCommentRel(Integer postId);

    PostCommentRel getSherPostCommentRelByComment(Integer commentId);

    void deletePostCommentRelByPost(Integer postId);

    void insertPostCommentRel(PostCommentRel postCommentRel);

}
