package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserLikeCommentDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserLikeComment userLikeComment = new UserLikeComment();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> userId = userLikeComment.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> commentId = userLikeComment.commentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserLikeComment extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> commentId = column("comment_id", JDBCType.INTEGER);

        public UserLikeComment() {
            super("user_like_comment");
        }
    }
}