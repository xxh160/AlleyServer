package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class UserAuthDSS {

    public static final UserAuth userAuth = new UserAuth();


    public static final SqlColumn<Integer> id = userAuth.id;


    public static final SqlColumn<Boolean> chat = userAuth.chat;


    public static final SqlColumn<Boolean> position = userAuth.position;


    public static final SqlColumn<Boolean> makeFriend = userAuth.makeFriend;


    public static final SqlColumn<Boolean> showWxInfo = userAuth.showWxInfo;


    public static final SqlColumn<Boolean> official = userAuth.official;


    public static final class UserAuth extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Boolean> chat = column("chat", JDBCType.BIT);

        public final SqlColumn<Boolean> position = column("position", JDBCType.BIT);

        public final SqlColumn<Boolean> makeFriend = column("make_friend", JDBCType.BIT);

        public final SqlColumn<Boolean> showWxInfo = column("show_wx_info", JDBCType.BIT);

        public final SqlColumn<Boolean> official = column("official", JDBCType.BIT);

        public UserAuth() {
            super("user_auth");
        }
    }
}