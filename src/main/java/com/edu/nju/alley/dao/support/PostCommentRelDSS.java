package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class PostCommentRelDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final PostCommentRel postCommentRel = new PostCommentRel();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> postId = postCommentRel.postId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> commentId = postCommentRel.commentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class PostCommentRel extends SqlTable {
        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> commentId = column("comment_id", JDBCType.INTEGER);

        public PostCommentRel() {
            super("post_comment_rel");
        }
    }
}