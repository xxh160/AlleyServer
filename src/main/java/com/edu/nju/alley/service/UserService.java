package com.edu.nju.alley.service;

import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.vo.ResponseVO;

public interface UserService {
    ResponseVO getUserPost(Integer userId, Integer pageId);

    ResponseVO getUserComment(Integer userId, Integer pageId);

    ResponseVO getUserLike(Integer userId, Integer pageId);

    ResponseVO viewUser(Integer userId);

    ResponseVO updateUser(Integer userId, UserDTO userDTO);

}
