package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserLikePostDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserLikePost userLikePost = new UserLikePost();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> userId = userLikePost.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> postId = userLikePost.postId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserLikePost extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public UserLikePost() {
            super("user_like_post");
        }
    }
}