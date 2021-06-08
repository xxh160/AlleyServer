package com.edu.nju.alley.controller;

import com.edu.nju.alley.dto.AuthenticationDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.service.UserService;
import com.edu.nju.alley.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "user")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("返回该用户的所有帖子详情")
    @GetMapping("/post/{userId}")
    public ResponseVO<List<PostVO>> getUserPost(@PathVariable Integer userId) {
        return ResponseVO.<List<PostVO>>success().add(userService.getUserPost(userId));
    }

    @ApiOperation("返回用户的所有评论")
    @GetMapping("/comment/{userId}")
    public ResponseVO<List<CommentVO>> getUserComment(@PathVariable Integer userId) {
        return ResponseVO.<List<CommentVO>>success().add(userService.getUserComment(userId));
    }

    @ApiOperation("返回用户所有点赞记录")
    @GetMapping("/like/{userId}")
    public ResponseVO<List<UserActionVO>> getUserLike(@PathVariable Integer userId) {
        return ResponseVO.<List<UserActionVO>>success().add(userService.getUserLike(userId));
    }

    @ApiOperation("查看用户信息")
    @GetMapping("/view/{userId}")
    public ResponseVO<UserVO> viewUser(@PathVariable Integer userId) {
        return ResponseVO.<UserVO>success().add(userService.viewUser(userId));
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update/{userId}")
    public ResponseVO<Object> updateUser(@PathVariable Integer userId,
                                         @RequestBody UserDTO userDTO) {
        userService.updateUser(userId, userDTO);
        return ResponseVO.success();
    }

    @ApiOperation("获得用户是否点赞；通过userId定位用户，typeId定位点赞种类，0是post，1是comment，targetId是对应实体的id")
    @GetMapping("/isLike")
    public ResponseVO<LikeVO> isLike(@RequestParam Integer userId,
                                     @RequestParam Integer typeId,
                                     @RequestParam Integer targetId) {
        return ResponseVO.<LikeVO>success().add(userService.isLike(userId, typeId, targetId));
    }

    @ApiOperation("官方用户认证")
    @PostMapping("/authenticate/{userId}")
    public ResponseVO<Object> authenticate(@PathVariable Integer userId,
                                           @RequestBody AuthenticationDTO authenticationDTO) {
        userService.authenticate(userId, authenticationDTO);
        return ResponseVO.success();
    }

    @ApiOperation("用户登录或者注册(第一次使用本系统的微信用户)；如果用户对应的openid在数据库内有记录则更新name和avatar和gender，反之加记录，同时openid需要调用微信api获取，前端需要传登录code")
    @PostMapping("/login")
    public ResponseVO<NewRecordVO> login(@RequestParam String code,
                                         @RequestParam String name,
                                         @RequestParam Integer gender,
                                         @RequestParam String avatarUrl) {
        return ResponseVO.<NewRecordVO>success().add(userService.login(code, name, gender, avatarUrl));
    }

    @ApiOperation("返回用户固定信息；返回用户用户名和头像url，这两者在本系统内不支持修改")
    @GetMapping("/info/{userId}")
    public ResponseVO<UserViewVO> getUserInfo(@PathVariable Integer userId) {
        return ResponseVO.<UserViewVO>success().add(userService.getUserInfo(userId));
    }

    @ApiOperation("所有点赞我的")
    @GetMapping("/like/all/{userId}")
    public ResponseVO<List<UserActionVO>> allLikeMe(@PathVariable Integer userId) {
        return ResponseVO.<List<UserActionVO>>success().add(userService.allLikeMe(userId));
    }

    @ApiOperation("所有评论我的")
    @GetMapping("/comment/all/{userId}")
    public ResponseVO<List<UserActionVO>> allCommentMe(@PathVariable Integer userId) {
        return ResponseVO.<List<UserActionVO>>success().add(userService.allCommentMe(userId));
    }

    @ApiOperation("获取通知列表")
    @GetMapping("/notification/all/{userId}")
    public ResponseVO<List<NotificationVO>> getNotifications(@PathVariable Integer userId) {
        return ResponseVO.<List<NotificationVO>>success().add(userService.getNotifications(userId));
    }

    @ApiOperation("标识某一条通知已阅")
    @GetMapping("/notification/check")
    public ResponseVO<Object> checkNotification(@RequestParam Integer userId,
                                                @RequestParam Integer notificationId) {
        userService.checkNotification(userId, notificationId);
        return ResponseVO.success();
    }

    @ApiOperation("获取用户发过的帖子简略信息")
    @GetMapping("/post/intro/{userId}")//待补充
    public ResponseVO<List<PostIntroVO>> getUserPostIntro(@PathVariable Integer userId) {
        return ResponseVO.<List<PostIntroVO>>success().add(userService.getUserPostIntro(userId));
    }

    @ApiOperation("获取用户评论过的帖子的简略信息")
    @GetMapping("/comment/intro/{userId}")//待补充
    public ResponseVO<List<PostIntroVO>> getUserCommentPostIntro(@PathVariable Integer userId) {
        return ResponseVO.<List<PostIntroVO>>success().add(userService.getUserCommentPostIntro(userId));
    }

    @ApiOperation("获取用户点赞过的帖子的简略信息")
    @GetMapping("/like/intro/{userId}")//待补充
    public ResponseVO<List<PostIntroVO>> getUserLikePostIntro(@PathVariable Integer userId) {
        return ResponseVO.<List<PostIntroVO>>success().add(userService.getUserLikePostIntro(userId));
    }


}
