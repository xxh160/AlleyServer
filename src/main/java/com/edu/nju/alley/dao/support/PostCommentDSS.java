package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class PostCommentDSS {

    public static final PostComment postComment = new PostComment();

    public static final SqlColumn<Integer> id = postComment.id;

    public static final SqlColumn<Integer> userId = postComment.userId;

    public static final SqlColumn<Integer> postId = postComment.postId;

    public static final SqlColumn<String> content = postComment.content;

    public static final SqlColumn<Integer> likeNum = postComment.likeNum;

    public static final class PostComment extends SqlTable {
        
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public final SqlColumn<String> content = column("content", JDBCType.CHAR);

        public final SqlColumn<Integer> likeNum = column("like_num", JDBCType.INTEGER);

        public PostComment() {
            super("post_comment");
        }
    }

}