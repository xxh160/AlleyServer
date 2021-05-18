package com.edu.nju.alley.dao.support;

import com.edu.nju.alley.enums.UpperType;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;
import java.util.Date;

public final class CommentDSS {

    public static final Comment comment = new Comment();


    public static final SqlColumn<Integer> id = comment.id;


    public static final SqlColumn<Integer> userId = comment.userId;


    public static final SqlColumn<Integer> upperId = comment.upperId;


    public static final SqlColumn<UpperType> upperType = comment.upperType;


    public static final SqlColumn<String> content = comment.content;


    public static final SqlColumn<Integer> likeNum = comment.likeNum;


    public static final SqlColumn<Date> createT = comment.createT;


    public static final SqlColumn<Date> lastModifiedT = comment.lastModifiedT;


    public static final class Comment extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> upperId = column("upper_id", JDBCType.INTEGER);

        public final SqlColumn<UpperType> upperType = column("upper_type", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Integer> likeNum = column("like_num", JDBCType.INTEGER);

        public final SqlColumn<Date> createT = column("create_t", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> lastModifiedT = column("last_modified_t", JDBCType.TIMESTAMP);

        public Comment() {
            super("comment");
        }
    }
}