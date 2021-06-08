package com.edu.nju.alley.controller;

import com.edu.nju.alley.dto.AuthenticationDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.service.UserService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "user")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("返回该用户的所有帖子;通过user id定位帖子，通过page id定位页数")
    @GetMapping("/post/{userId}")
    public ResponseVO getUserPost(@PathVariable Integer userId,
                                  @RequestParam Integer pageId) {
        return ResponseVO.success().add(userService.getUserPost(userId, pageId));
    }

    @ApiOperation("返回用户的所有评论;通过user id找到用户所有评论并返回，通过page id定位页数")
    @GetMapping("/comment/{userId}")
    public ResponseVO getUserComment(@PathVariable Integer userId,
                                     @RequestParam Integer pageId) {
        return ResponseVO.success().add(userService.getUserComment(userId, pageId));
    }

    @ApiOperation("返回用户所有点赞记录;通过user_like_*查找点赞记录并返回，page id定位页数")
    @GetMapping("/like/{userId}")
    public ResponseVO getUserLike(@PathVariable Integer userId,
                                  @RequestParam Integer pageId) {
        return ResponseVO.success().add(userService.getUserLike(userId, pageId));
    }

    @ApiOperation("查看用户信息;通过user id定位用户并返回信息")
    @GetMapping("/view/{userId}")
    public ResponseVO viewUser(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.viewUser(userId));
    }

    @ApiOperation("更新用户信息;通过request body的信息更新用户信息")
    @PostMapping("/update/{userId}")
    public ResponseVO updateUser(@PathVariable Integer userId,
                                 @RequestBody UserDTO userDTO) {
        userService.updateUser(userId, userDTO);
        return ResponseVO.success();
    }

    @ApiOperation("获得用户是否点赞;通过userId定位用户，typeId定位点赞种类，0是post，1是comment，targetId是对应实体的id")
    @GetMapping("/isLike")
    public ResponseVO isLike(@RequestParam Integer userId,
                             @RequestParam Integer typeId,
                             @RequestParam Integer targetId) {
        return ResponseVO.success().add(userService.isLike(userId, typeId, targetId));
    }

    @ApiOperation("官方用户认证;通过user id定位用户，对比invitation code是否相同。若通过则更新其权限，同时更新invitation_code中user id。")
    @PostMapping("/authenticate/{userId}")
    public ResponseVO authenticate(@PathVariable Integer userId,
                                   @RequestBody AuthenticationDTO authenticationDTO) {
        userService.authenticate(userId, authenticationDTO);
        return ResponseVO.success();
    }

    @ApiOperation("用户登录或者注册(第一次使用本系统的微信用户);如果用户对应的openid在数据库内有记录则更新name和avatar和gender，反之加记录，同时openid需要调用微信api获取，前端需要传登录code")
    @PostMapping("/login")
    public ResponseVO login(@RequestParam String code,
                            @RequestParam String name,
                            @RequestParam Integer gender,
                            @RequestParam String avatarUrl) {
        return ResponseVO.success().add(userService.login(code, name, gender, avatarUrl));
    }

    @ApiOperation("返回用户固定信息;返回用户用户名和头像url，这两者在本系统内不支持修改")
    @GetMapping("/info/{userId}")
    public ResponseVO getUserInfo(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.getUserInfo(userId));
    }

    @ApiOperation("")
    @GetMapping("/like/all/{userId}")
    public ResponseVO allLikeMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.allLikeMe(userId));
    }

    @ApiOperation("")
    @GetMapping("/comment/all/{userId}")
    public ResponseVO allCommentMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.allCommentMe(userId));
    }

    @ApiOperation("")
    @GetMapping("/like/new/{userId}")
    public ResponseVO newLikeMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.NewLikeMe(userId));
    }

    @ApiOperation("")
    @GetMapping("/comment/new/{userId}")
    public ResponseVO newCommentMe(@PathVariable Integer userId) {
        return ResponseVO.success().add(userService.NewCommentMe(userId));
    }
}
