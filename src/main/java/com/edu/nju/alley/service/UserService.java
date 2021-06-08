package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.AuthenticationDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.po.User;
import com.edu.nju.alley.po.UserAuth;
import com.edu.nju.alley.vo.*;

import java.util.List;

public interface UserService {

    List<PostVO> getUserPost(Integer userId);

    List<CommentVO> getUserComment(Integer userId);

    List<UserActionVO> getUserLike(Integer userId);

    UserVO viewUser(Integer userId);

    void updateUser(Integer userId, UserDTO userDTO);

    LikeVO isLike(Integer userId, Integer typeId, Integer targetId);

    void authenticate(Integer userId, AuthenticationDTO authenticationDTO);

    NewRecordVO login(String code, String name, Integer gender, String avatarUrl);

    UserViewVO getUserInfo(Integer userId);

    List<UserActionVO> allLikeMe(Integer userId);

    List<UserActionVO> allCommentMe(Integer userId);

    List<UserActionVO> NewLikeMe(Integer userId);

    List<UserActionVO> NewCommentMe(Integer userId);

    List<PostIntroVO> getUserPostIntro(Integer userId);

    List<PostIntroVO> getUserCommentPostIntro(Integer userId);

    List<PostIntroVO> getUserLikePostIntro(Integer userId);

    User getSherUser(Integer userId);

    UserAuth getSherUserAuth(Integer userAuthId);


}
