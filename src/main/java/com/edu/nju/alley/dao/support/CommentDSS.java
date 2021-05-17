package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class CommentDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Comment comment = new Comment();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = comment.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> userId = comment.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> upperId = comment.upperId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> upperType = comment.upperType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> content = comment.content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> likeNum = comment.likeNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createT = comment.createT;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> lastModifiedT = comment.lastModifiedT;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Comment extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> upperId = column("upper_id", JDBCType.INTEGER);

        public final SqlColumn<String> upperType = column("upper_type", JDBCType.CHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Integer> likeNum = column("like_num", JDBCType.INTEGER);

        public final SqlColumn<Date> createT = column("create_t", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> lastModifiedT = column("last_modified_t", JDBCType.TIMESTAMP);

        public Comment() {
            super("comment");
        }
    }
}