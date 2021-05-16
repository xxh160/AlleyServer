package com.edu.nju.alley.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final User user = new User();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = user.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gender = user.gender;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> personalIntroduction = user.personalIntroduction;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> allowChat = user.allowChat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> allowFriendRequest = user.allowFriendRequest;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> allowSharePosition = user.allowSharePosition;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class User extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> gender = column("gender", JDBCType.VARCHAR);

        public final SqlColumn<String> personalIntroduction = column("personal_introduction", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> allowChat = column("allow_chat", JDBCType.BIT);

        public final SqlColumn<Boolean> allowFriendRequest = column("allow_friend_request", JDBCType.BIT);

        public final SqlColumn<Boolean> allowSharePosition = column("allow_share_position", JDBCType.BIT);

        public User() {
            super("user");
        }
    }
}