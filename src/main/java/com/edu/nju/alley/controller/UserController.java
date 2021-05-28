package com.edu.nju.alley.controller;

import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.service.UserService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/post/{userId}")
    public ResponseVO getUserPost(Integer userId, Integer pageId) {
        return userService.getUserPost(userId, pageId);
    }

    @GetMapping("/comment/{userId}")
    public ResponseVO getUserComment(Integer userId, Integer pageId) {
        return userService.getUserComment(userId, pageId);
    }

    @GetMapping("/like/{userId}")
    public ResponseVO getUserLike(Integer userId, Integer pageId) {
        return userService.getUserLike(userId, pageId);
    }

    @GetMapping("/view/{userId}")
    public ResponseVO viewUser(Integer userId) {
        return userService.viewUser(userId);
    }

    @GetMapping("/update/{userId}")
    public ResponseVO updateUser(Integer userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

}
