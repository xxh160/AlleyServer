package com.edu.nju.alley.po;

import lombok.Data;

@Data
public class InvitationCode {

    private Integer id;

    private String code;

    private Integer userId;

    private String description;

}