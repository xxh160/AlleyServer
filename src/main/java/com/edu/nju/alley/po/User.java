package com.edu.nju.alley.po;

import com.edu.nju.alley.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Integer id;

    private Integer authId;

    private String sign;

    private String openid;

    private Integer gender;

    private String name;

    private String avatar;

    public void updateByDTO(UserDTO userDTO) {
        // user的name gender avatar 在我们小程序内不支持修改
        this.sign = userDTO.getSign();
    }

    public void updateLogin(String name, Integer gender, String avatar) {
        this.name = name;
        this.gender = gender;
        this.avatar = avatar;
    }

    public static User defaultUser(String openid) {
        User user = new User();
        user.setOpenid(openid);
        user.setSign("这个人啥都没说喔。");
        return user;
    }
}