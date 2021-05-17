package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserPostRelDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserPostRel userPostRel = new UserPostRel();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> userId = userPostRel.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> postId = userPostRel.postId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserPostRel extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public UserPostRel() {
            super("user_post_rel");
        }
    }
}