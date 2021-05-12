package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class PostCommentPODynamicSqlSupport {

    public static final PostCommentPO postCommentPO = new PostCommentPO();

    public static final SqlColumn<Integer> id = postCommentPO.id;

    public static final SqlColumn<Integer> userId = postCommentPO.userId;

    public static final SqlColumn<Integer> postId = postCommentPO.postId;

    public static final SqlColumn<String> content = postCommentPO.content;

    public static final SqlColumn<Integer> likeNum = postCommentPO.likeNum;

    public static final class PostCommentPO extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public final SqlColumn<String> content = column("content", JDBCType.CHAR);

        public final SqlColumn<Integer> likeNum = column("like_num", JDBCType.INTEGER);

        public PostCommentPO() {
            super("post_comment");
        }
    }
}