package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class UserPostRelDSS {

    public static final UserPostRel userPostRel = new UserPostRel();


    public static final SqlColumn<Integer> userId = userPostRel.userId;


    public static final SqlColumn<Integer> postId = userPostRel.postId;

    
    public static final class UserPostRel extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public UserPostRel() {
            super("user_post_rel");
        }
    }
}