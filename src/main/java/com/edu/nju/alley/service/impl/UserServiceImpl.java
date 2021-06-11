package com.edu.nju.alley.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.edu.nju.alley.config.WechatConfig;
import com.edu.nju.alley.dao.UserAuthMapper;
import com.edu.nju.alley.dao.UserMapper;
import com.edu.nju.alley.dao.support.UserDSS;
import com.edu.nju.alley.dto.AuthenticationDTO;
import com.edu.nju.alley.dto.UserDTO;
import com.edu.nju.alley.enums.Msg;
import com.edu.nju.alley.enums.Type;
import com.edu.nju.alley.exceptions.NoSuchDataException;
import com.edu.nju.alley.po.*;
import com.edu.nju.alley.service.*;
import com.edu.nju.alley.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class UserServiceImpl implements UserService {

    private final PostService postService;

    private final CommentService commentService;

    private final PostCommentService postCommentService;

    private final UserMapper userMapper;

    private final UserAuthMapper userAuthMapper;

    private final AuthenticationService authenticationService;

    private final WechatService wechatService;

    private final WechatConfig wechat;

    private final UserCommentService userCommentService;

    private final UserPostService userPostService;


    @Autowired
    public UserServiceImpl(PostService postService,
                           CommentService commentService,
                           PostCommentService postCommentService,
                           UserMapper userMapper,
                           UserAuthMapper userAuthMapper,
                           AuthenticationService authenticationService,
                           WechatService wechatService,
                           WechatConfig wechat,
                           UserCommentService userCommentService,
                           UserPostService userPostService) {
        this.postService = postService;
        this.commentService = commentService;
        this.postCommentService = postCommentService;
        this.userMapper = userMapper;
        this.userAuthMapper = userAuthMapper;
        this.authenticationService = authenticationService;
        this.wechatService = wechatService;
        this.wechat = wechat;
        this.userCommentService = userCommentService;
        this.userPostService = userPostService;
    }

    @Override
    public List<PostVO> getUserPost(Integer userId) {
        // 返回用户所有帖子
        return this.userPostService.getSherUserPostRel(userId).stream()
                .map(t -> postService.getSpecificPost(t.getPostId()))
                .sorted(Comparator.comparing(PostVO::getCreateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentVO> getUserComment(Integer userId) {
        //返回用户所有评论
        return userCommentService.getSherUserCommentRel(userId).stream()
                .map(t -> commentService.getSpecificComment(t.getCommentId()))
                .sorted(Comparator.comparing(CommentVO::getCreateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserActionVO> getUserLike(Integer userId) {
        //返回点赞的帖子和评论
        List<UserActionVO> userActionVOList = new ArrayList<>();
        userCommentService.getSherUserLikeComment(userId)
                .forEach(t -> userActionVOList.add(new UserActionVO(t)));
        userPostService.getSherUserLikePost(userId)
                .forEach(t -> userActionVOList.add(new UserActionVO(t)));
        return userActionVOList;
    }

    @Override
    public UserVO viewUser(Integer userId) {
        //得到用户基本信息
        User user = this.getSherUser(userId);
        if (user == null) throw new NoSuchDataException(Msg.NoSuchUserError.getMsg());
        //得到用户权限数据
        UserAuth userAuth = this.getSherUserAuth(user.getAuthId());
        if (userAuth == null) throw new NoSuchDataException(Msg.NoSuchAuthError.getMsg());
        return new UserVO(user, new UserAuthVO(userAuth));
    }

    @Override
    public void updateUser(Integer userId, UserDTO userDTO) {
        User user = this.getSherUser(userId);
        if (user == null) throw new NoSuchDataException(Msg.NoSuchUserError.getMsg());

        user.updateByDTO(userDTO);

        UserAuth userAuth = this.getSherUserAuth(user.getAuthId());
        if (userAuth == null) throw new NoSuchDataException(Msg.NoSuchAuthError.getMsg());

        userAuth.updateByDTO(userDTO.getAuth());

        userMapper.updateByPrimaryKeySelective(user);
        userAuthMapper.updateByPrimaryKeySelective(userAuth);
    }

    @Override
    public LikeVO isLike(Integer userId, Integer typeId, Integer targetId) {
        if (typeId.equals(Type.POST.getCode()))
            return new LikeVO(userPostService.getSherUserLikePost(userId, targetId) != null);
        return new LikeVO(userCommentService.getSherUserLikeComment(userId, targetId) != null);
    }

    @Override
    public void authenticate(Integer userId, AuthenticationDTO authenticationDTO) {
        Integer codeId = authenticationService.isExist(authenticationDTO.getCode());
        if (codeId == null)
            throw new NoSuchDataException(Msg.AuthCodeError.getMsg());

        authenticationService.addUser(userId, codeId);

        User user = this.getSherUser(userId);
        if (user == null) throw new NoSuchDataException(Msg.NoSuchUserError.getMsg());
        UserAuth userAuth = this.getSherUserAuth(user.getAuthId());
        if (userAuth == null) throw new NoSuchDataException(Msg.NoSuchAuthError.getMsg());

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
            if (errCode != 0) throw new NoSuchDataException(Msg.WechatError.getMsg() + errCode);
        }

        String openid = json.getStr("openid");
        // 通过openid找用户
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
        User user = this.getSherUser(userId);
        if (user == null) throw new NoSuchDataException(Msg.NoSuchUserError.getMsg());
        return new UserViewVO(user.getName(), user.getAvatar());
    }

    @Override
    public List<UserActionVO> allLikeMe(Integer userId) {
        // 只有紧跟在page helper后的第一个查询会被分页
        // 所以没法分页 艹 艹 艹 艹 艹
        List<UserActionVO> all = new ArrayList<>();
        // 分为post和comment两块
        // post，根据userid从user_post_rel中取出user所有的postId，在user_like_post中寻找所有和postId相关的userId
        List<UserPostRel> userPostRelList = userPostService.getSherUserPostRel(userId);

        userPostRelList.forEach(cur -> all.addAll(userPostService
                .getSherUserLikePostByPost(cur.getPostId())
                .stream().map(UserActionVO::new).collect(Collectors.toList())));
        // comment类似
        List<UserCommentRel> userCommentRelList = userCommentService.getSherUserCommentRel(userId);

        userCommentRelList.forEach(cur -> all.addAll(userCommentService
                .getSherUserLikeCommentByComment(cur.getCommentId())
                .stream().map(UserActionVO::new).collect(Collectors.toList())));

        return all;
    }

    @Override
    public List<UserActionVO> allCommentMe(Integer userId) {
        List<UserActionVO> all = new ArrayList<>();

        List<UserPostRel> userPostRelList = userPostService.getSherUserPostRel(userId);

        userPostRelList.forEach(cur -> all.addAll(postService.getPostComments(cur.getPostId())
                .stream().map(t -> UserActionVO.userCommentAPost(t.getUserId(), cur.getPostId()))
                .collect(Collectors.toList())));

        List<UserCommentRel> userCommentRelList = userCommentService.getSherUserCommentRel(userId);

        userCommentRelList.forEach(cur -> all.addAll(commentService.getChildComments(cur.getCommentId())
                .stream().map(t -> UserActionVO.userCommentAComment(t.getUserId(), cur.getCommentId()))
                .collect(Collectors.toList())));

        return all;
    }

    @Override
    public List<NotificationVO> getNotifications(Integer userId) {
        return null;
    }

    @Override
    public void checkNotification(Integer userId, Integer notificationId) {
    }

    @Override
    public List<PostIntroVO> getUserPostIntro(Integer userId) {
        return userPostService.getSherUserPostRel(userId)
                .stream()
                .map(c -> postService.getSherPost(c.getPostId()))
                .sorted(Comparator.comparing(Post::getCreateT))
                .map(PostIntroVO::new)
                .distinct() // 去重
                .collect(Collectors.toList());
    }

    @Override
    public List<PostIntroVO> getUserCommentPostIntro(Integer userId) {
        return userCommentService.getSherUserCommentRel(userId)
                .stream()
                .map(c -> postService.getSherPost(postCommentService.getSherPostCommentRelByComment(c.getCommentId()).getPostId()))
                .sorted(Comparator.comparing(Post::getCreateT))
                .map(PostIntroVO::new)
                .distinct() // 去重
                .collect(Collectors.toList());
    }

    @Override
    public List<PostIntroVO> getUserLikePostIntro(Integer userId) {
        List<Post> all = userPostService
                .getSherUserLikePost(userId)
                .stream()
                .map(c -> postService.getSherPost(c.getPostId()))
                .collect(Collectors.toList());

        all.addAll(userCommentService
                .getSherUserLikeComment(userId)
                .stream()
                .map(c -> postService.getSherPost(postCommentService.getSherPostCommentRelByComment(c.getCommentId()).getPostId()))
                .collect(Collectors.toList()));

        return all.stream()
                .sorted(Comparator.comparing(Post::getCreateT))
                .map(PostIntroVO::new)
                .distinct().collect(Collectors.toList()); // 去重
    }

    // 工具方法，负责返回各种PO
    // 工具方法不抛异常，具体由调用函数自己决定
    @Override
    public User getSherUser(Integer userId) {
        Optional<User> userOptional = userMapper.selectOne(c -> c.where(UserDSS.id, isEqualTo(userId)));
        if (userOptional.isEmpty()) return null;
        return userOptional.get();
    }

    @Override
    public UserAuth getSherUserAuth(Integer userAuthId) {
        Optional<UserAuth> userAuthOptional = userAuthMapper.selectByPrimaryKey(userAuthId);
        if (userAuthOptional.isEmpty()) return null;
        return userAuthOptional.get();
    }


}
