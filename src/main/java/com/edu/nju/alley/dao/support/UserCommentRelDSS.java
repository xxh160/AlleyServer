package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserCommentRelDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final UserCommentRel userCommentRel = new UserCommentRel();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> userId = userCommentRel.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> commentId = userCommentRel.commentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class UserCommentRel extends SqlTable {
        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> commentId = column("comment_id", JDBCType.INTEGER);

        public UserCommentRel() {
            super("user_comment_rel");
        }
    }
}