package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class PostCommentRelDSS {

    public static final PostCommentRel postCommentRel = new PostCommentRel();


    public static final SqlColumn<Integer> postId = postCommentRel.postId;


    public static final SqlColumn<Integer> commentId = postCommentRel.commentId;

    
    public static final class PostCommentRel extends SqlTable {
        public final SqlColumn<Integer> postId = column("post_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> commentId = column("comment_id", JDBCType.INTEGER);

        public PostCommentRel() {
            super("post_comment_rel");
        }
    }
}