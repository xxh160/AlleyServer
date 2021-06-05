package com.edu.nju.alley.enums;

import lombok.Getter;

@Getter
public enum Msg {

    AuthCodeError("错误的邀请码"),
    NoSuchCommentError("没有这条评论"),
    NoSuchPostError("没有这条帖子"),
    NoSuchUserError("没有这个用户"),
    NoSuchAuthError("没有对应的权限"),
    NoRelError("缺少相关信息，如作者等"),
    WechatError("wechat请求失败，错误码为: "),
    UnknownError("未知错误，可能原因是：");

    private final String msg;

    Msg(String m) {
        this.msg = m;
    }

}
