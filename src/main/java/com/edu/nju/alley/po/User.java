package com.edu.nju.alley.po;

import com.edu.nju.alley.dto.UserDTO;
import lombok.Data;

@Data
public class User {

    private Integer id;

    private Integer authId;

    private String sign;

    private String openid;

    public void updateByDTO(UserDTO userDTO) {
        this.sign = userDTO.getSign();
    }

}