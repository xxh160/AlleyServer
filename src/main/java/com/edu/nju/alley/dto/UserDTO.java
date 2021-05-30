package com.edu.nju.alley.dto;

import com.edu.nju.alley.vo.PostVO;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Integer openId; // 微信账户标识码

    private String sign;

    private List<PostVO> posts;

    private UserAuthDTO auth;

}
