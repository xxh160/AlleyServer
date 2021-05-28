package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.User;
import com.edu.nju.alley.po.UserAuth;
import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    private Integer id;
    private Integer openid;//微信账号唯一的标识码
    private String sign;
    private List<PostVO> posts;
    private UserAuthVO auth;

    public UserVO(User user, List<PostVO> posts, UserAuth userAuth) {
        id = user.getId();
        //openid=user.getId();
        sign = user.getSign();
        this.posts = posts;
        this.auth = new UserAuthVO(userAuth);
    }
}
