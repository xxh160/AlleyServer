package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;

public final class PostDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Post post = new Post();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = post.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> authId = post.authId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> userId = post.userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> title = post.title;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> content = post.content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> likeNum = post.likeNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> commentNum = post.commentNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createT = post.createT;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> lastModifiedT = post.lastModifiedT;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> anchorId = post.anchorId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> addrX = post.addrX;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> addrY = post.addrY;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Post extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> authId = column("auth_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<String> content = column("content", JDBCType.VARCHAR);

        public final SqlColumn<Integer> likeNum = column("like_num", JDBCType.INTEGER);

        public final SqlColumn<Integer> commentNum = column("comment_num", JDBCType.INTEGER);

        public final SqlColumn<Date> createT = column("create_t", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> lastModifiedT = column("last_modified_t", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> anchorId = column("anchor_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> addrX = column("addr_x", JDBCType.INTEGER);

        public final SqlColumn<Integer> addrY = column("addr_y", JDBCType.INTEGER);

        public Post() {
            super("post");
        }
    }
}