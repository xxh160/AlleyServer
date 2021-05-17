package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class UserLikePostDSS {

    public static final UserLikePost userLikePost = new UserLikePost();


    public static final SqlColumn<Integer> userId = userLikePost.userId;


    public static final SqlColumn<Integer> postId = userLikePost.postId;

    
    public static final class UserLikePost extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public UserLikePost() {
            super("user_like_post");
        }
    }
}