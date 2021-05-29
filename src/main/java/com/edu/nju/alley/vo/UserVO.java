package com.edu.nju.alley.vo;

import com.edu.nju.alley.po.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserVO {

    private Integer id;

    private String openid; //微信账号唯一的标识码

    private String sign;

    private List<PostVO> posts;

    private UserAuthVO auth;

    public UserVO(User user, List<PostVO> posts, UserAuthVO userAuthVO) {
        id = user.getId();
        openid = user.getOpenid();
        sign = user.getSign();
        this.posts = posts;
        this.auth = userAuthVO;
    }
}
