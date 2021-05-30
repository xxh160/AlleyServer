package com.edu.nju.alley.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.edu.nju.alley.config.WechatConfig;
import com.edu.nju.alley.dao.*;
import com.edu.nju.alley.dao.support.*;
import com.edu.nju.alley.dto.AuthenticationDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.enums.LikeType;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import com.edu.nju.alley.po.User;
import com.edu.nju.alley.po.UserAuth;
import com.edu.nju.alley.po.UserCommentRel;
import com.edu.nju.alley.po.UserPostRel;
import com.edu.nju.alley.service.*;
import com.edu.nju.alley.util.Const;
import com.edu.nju.alley.vo.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class UserServiceImpl implements UserService {

    private final PostService postService;

    private final CommentService commentService;

    private final UserMapper userMapper;

    private final UserLikeCommentMapper userLikeCommentMapper;

    private final UserLikePostMapper userLikePostMapper;

    private final UserPostRelMapper userPostRelMapper;

    private final UserCommentRelMapper userCommentRelMapper;

    private final UserAuthMapper userAuthMapper;

    private final AuthenticationService authenticationService;

    private final WechatService wechatService;

    private final WechatConfig.Wechat wechat;

    @Autowired
    public UserServiceImpl(PostService postService,
                           CommentService commentService,
                           UserMapper userMapper,
                           UserLikeCommentMapper userLikeCommentMapper,
                           UserLikePostMapper userLikePostMapper,
                           UserPostRelMapper userPostRelMapper,
                           UserCommentRelMapper userCommentRelMapper,
                           UserAuthMapper userAuthMapper,
                           AuthenticationService authenticationService,
                           WechatService wechatService,
                           WechatConfig.Wechat wechat) {
        this.postService = postService;
        this.commentService = commentService;
        this.userMapper = userMapper;
        this.userLikeCommentMapper = userLikeCommentMapper;
        this.userLikePostMapper = userLikePostMapper;
        this.userPostRelMapper = userPostRelMapper;
        this.userCommentRelMapper = userCommentRelMapper;
        this.userAuthMapper = userAuthMapper;
        this.authenticationService = authenticationService;
        this.wechatService = wechatService;
        this.wechat = wechat;
    }

    @Override
    public List<PostVO> getUserPost(Integer userId, Integer pageId) {
        // 返回用户所有帖子
        PageHelper.startPage(pageId, Const.pageSize);
        List<UserPostRel> userPostRels = userPostRelMapper
                .select(c -> c.where(UserPostRelDSS.userId, isEqualTo(userId)));
        return userPostRels.stream()
                .map(t -> postService.getSpecificPost(t.getPostId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentVO> getUserComment(Integer userId, Integer pageId) {
        //返回用户所有评论
        PageHelper.startPage(pageId, Const.pageSize);
        List<UserCommentRel> userCommentRels = userCommentRelMapper
                .select(c -> c.where(UserCommentRelDSS.userId, isEqualTo(userId)));
        return userCommentRels.stream()
                .map(t -> commentService.getSpecificOne(t.getCommentId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserLikeVO> getUserLike(Integer userId, Integer pageId) {
        PageHelper.startPage(pageId, Const.pageSize);
        //是返回点赞的帖子还是评论？
        List<UserLikeVO> userLikeVOList = new ArrayList<>();
        userLikeCommentMapper.select(c -> c.where(UserLikeCommentDSS.userId, isEqualTo(userId)))
                .forEach(t -> userLikeVOList.add(new UserLikeVO(t)));
        userLikePostMapper.select(c -> c.where(UserLikePostDSS.userId, isEqualTo(userId)))
                .forEach(t -> userLikeVOList.add(new UserLikeVO(t)));
        return userLikeVOList;
    }

    @Override
    public UserVO viewUser(Integer userId) {
        //查看用户信息
        //得到用户基本信息
        Optional<User> userOptional = userMapper
                .selectOne(c -> c.where(UserDSS.id, isEqualTo(userId)));
        if (userOptional.isEmpty()) throw new NoSuchDataException("没有这个用户");
        User user = userOptional.get();
        //得到用户所有帖子
        List<UserPostRel> userPostRels = userPostRelMapper
                .select(c -> c.where(UserPostRelDSS.userId, isEqualTo(userId)));

        List<PostVO> postVOList = userPostRels.stream()
                .map(t -> postService.getSpecificPost(t.getPostId()))
                .collect(Collectors.toList());
        //得到用户权限数据
        Optional<UserAuth> userAuthOptional = userAuthMapper.selectByPrimaryKey(user.getAuthId());
        if (userAuthOptional.isEmpty()) throw new NoSuchDataException("没有这条权限");
        return new UserVO(user, postVOList, new UserAuthVO(userAuthOptional.get()));
    }

    @Override
    public void updateUser(Integer userId, UserDTO userDTO) {
        Optional<User> userOptional = userMapper
                .selectOne(c -> c.where(UserDSS.id, isEqualTo(userId)));
        if (userOptional.isEmpty()) throw new NoSuchDataException("没有这个用户");
        User user = userOptional.get();
        user.updateByDTO(userDTO);

        Optional<UserAuth> userAuthOptional = userAuthMapper.selectByPrimaryKey(user.getAuthId());
        if (userAuthOptional.isEmpty()) throw new NoSuchDataException("没有这条权限");
        UserAuth userAuth = userAuthOptional.get();
        userAuth.updateByDTO(userDTO.getAuth());

        userMapper.updateByPrimaryKeySelective(user);
        userAuthMapper.updateByPrimaryKeySelective(userAuth);
    }

    @Override
    public LikeVO isLike(Integer userId, Integer typeId, Integer targetId) {
        if (typeId.equals(LikeType.POST.getCode())) {
            Optional<UserPostRel> userPostRelOptional = userPostRelMapper
                    .selectOne(c -> c.where(UserPostRelDSS.postId, isEqualTo(targetId))
                            .and(UserPostRelDSS.userId, isEqualTo(userId)));
            return new LikeVO(userPostRelOptional.isPresent());
        }
        Optional<UserCommentRel> userCommentRelOptional = userCommentRelMapper
                .selectOne(c -> c.where(UserPostRelDSS.postId, isEqualTo(targetId))
                        .and(UserPostRelDSS.userId, isEqualTo(userId)));
        return new LikeVO(userCommentRelOptional.isPresent());
    }

    @Override
    public void authenticate(Integer userId, AuthenticationDTO authenticationDTO) {
        Integer codeId = authenticationService.isExist(authenticationDTO.getCode());
        if (codeId == null)
            throw new NoSuchDataException("错误的邀请码");
        authenticationService.addUser(userId, codeId);
        Optional<User> userOptional = userMapper.selectByPrimaryKey(userId);
        if (userOptional.isEmpty()) throw new NoSuchDataException("没有这个用户");
        Optional<UserAuth> userAuthOptional = userAuthMapper.selectByPrimaryKey(userOptional.get().getAuthId());
        if (userAuthOptional.isEmpty()) throw new NoSuchDataException("没有这条权限");
        UserAuth userAuth = userAuthOptional.get();
        userAuth.setOfficial(true);
        userAuthMapper.updateByPrimaryKeySelective(userAuth);
    }

    @Override
    public NewRecordVO login(String code, String name, Integer gender, String avatarUrl) {
        JSONObject json = JSONUtil.parseObj(wechatService.getUserOpenId(
                wechat.getAppId(),
                wechat.getAppSecret(),
                code,
                "authorization_code"));
        String error = json.getStr("errcode");
        if (error != null) {
            int errCode = Integer.parseInt(error);
            if (errCode != 0) throw new NoSuchDataException("wechat请求失败，错误码为: " + errCode);
        }
        String openid = json.getStr("openid");
        Optional<User> userOptional = userMapper
                .selectOne(c -> c.where(UserDSS.openid, isEqualTo(openid)));
        // 没有这个用户 注册
        if (userOptional.isEmpty()) {
            User user = User.defaultUser(openid);
            user.updateLogin(name, gender, avatarUrl);
            UserAuth userAuth = UserAuth.defaultUserAuth();
            userAuthMapper.insert(userAuth);
            user.setAuthId(userAuth.getId());
            userMapper.insert(user);
            return new NewRecordVO(user.getId());
        }
        // 有这个用户 更新
        User user = userOptional.get();
        user.updateLogin(name, gender, avatarUrl);
        userMapper.updateByPrimaryKeySelective(user);
        return new NewRecordVO(user.getId());
    }

    @Override
    public UserViewVO getUserInfo(Integer userId) {
        Optional<User> userOptional = userMapper.selectByPrimaryKey(userId);
        if (userOptional.isEmpty()) throw new NoSuchDataException("没有这个用户");
        User user = userOptional.get();
        return new UserViewVO(user.getName(), user.getAvatar());
    }

}
