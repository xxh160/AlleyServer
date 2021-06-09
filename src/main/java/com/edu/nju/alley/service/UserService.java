package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.AuthenticationDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.po.*;
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

    List<NotificationVO> getNotifications(Integer userId);

    void checkNotification(Integer userId, Integer notificationId);

    List<PostIntroVO> getUserPostIntro(Integer userId);

    List<PostIntroVO> getUserCommentPostIntro(Integer userId);

    List<PostIntroVO> getUserLikePostIntro(Integer userId);

    User getSherUser(Integer userId);

    UserAuth getSherUserAuth(Integer userAuthId);

    List<UserPostRel> getSherUserPostRel(Integer userId);

    List<UserCommentRel> getSherUserCommentRel(Integer userId);

    List<UserLikePost> getSherUserLikePost(Integer userId);

    List<UserLikeComment> getSherUserLikeComment(Integer userId);

}
