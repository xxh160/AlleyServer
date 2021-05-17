package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class PostAuthDSS {

    public static final PostAuth postAuth = new PostAuth();


    public static final SqlColumn<Integer> id = postAuth.id;


    public static final SqlColumn<Boolean> visible = postAuth.visible;

    
    public static final class PostAuth extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Boolean> visible = column("visible", JDBCType.BIT);

        public PostAuth() {
            super("post_auth");
        }
    }
}