package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class UserDSS {

    public static final User user = new User();

    public static final SqlColumn<Integer> id = user.id;

    public static final SqlColumn<Integer> authId = user.authId;

    public static final SqlColumn<String> sign = user.sign;

    public static final SqlColumn<String> openid = user.openid;


    public static final class User extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> authId = column("auth_id", JDBCType.INTEGER);

        public final SqlColumn<String> sign = column("sign", JDBCType.VARCHAR);

        public final SqlColumn<String> openid = column("openid", JDBCType.VARCHAR);

        public User() {
            super("customer");
        }
    }
}