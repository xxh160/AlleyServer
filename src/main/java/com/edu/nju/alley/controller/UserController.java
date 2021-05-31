package com.edu.nju.alley.controller;

import com.edu.nju.alley.dto.AuthenticationDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.service.UserService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/post/{userId}")
    public ResponseVO getUserPost(@PathVariable Integer userId,
                                  @RequestParam Integer pageId) {
        return ResponseVO.success().add(userService.getUserPost(userId, pageId));
    }

    @GetMapping("/comment/{userId}")
    public ResponseVO getUserComment(@PathVariable Integer userId,
                                     @RequestParam Integer pageId) {
        return ResponseVO.success().add(userService.getUserComment(userId, pageId));
    }

    @GetMapping("/like/{userId}")
    public ResponseVO getUserLike(@PathVariable Integer userId,
                                  @RequestParam Integer pageId) {
        return ResponseVO.success().add(userService.getUserLike(userId, pageId));
    }

    @GetMapping("/view/{userId}")
    public ResponseVO viewUser(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.viewUser(userId));
    }

    @PostMapping("/update/{userId}")
    public ResponseVO updateUser(@PathVariable Integer userId,
                                 @RequestBody UserDTO userDTO) {
        userService.updateUser(userId, userDTO);
        return ResponseVO.success();
    }

    @GetMapping("/isLike")
    public ResponseVO isLike(@RequestParam Integer userId,
                             @RequestParam Integer typeId,
                             @RequestParam Integer targetId) {
        return ResponseVO.success().add(userService.isLike(userId, typeId, targetId));
    }

    @PostMapping("/authenticate/{userId}")
    public ResponseVO authenticate(@PathVariable Integer userId,
                                   @RequestBody AuthenticationDTO authenticationDTO) {
        userService.authenticate(userId, authenticationDTO);
        return ResponseVO.success();
    }

    @PostMapping("/login")
    public ResponseVO login(@RequestParam String code,
                            @RequestParam String name,
                            @RequestParam Integer gender,
                            @RequestParam String avatarUrl) {
        return ResponseVO.success().add(userService.login(code, name, gender, avatarUrl));
    }

    @GetMapping("/info/{userId}")
    public ResponseVO getUserInfo(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.getUserInfo(userId));
    }

    @GetMapping("/like/all/{userId}")
    public ResponseVO allLikeMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.allLikeMe(userId));
    }

    @GetMapping("/comment/all/{userId}")
    public ResponseVO allCommentMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.allCommentMe(userId));
    }

    @GetMapping("/like/new/{userId}")
    public ResponseVO newLikeMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.NewLikeMe(userId));
    }

    @GetMapping("/comment/new/{userId}")
    public ResponseVO newCommentMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.NewCommentMe(userId));
    }
}
