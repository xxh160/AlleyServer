package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;
import java.util.Date;

public final class PostDSS {

    public static final Post post = new Post();


    public static final SqlColumn<Integer> id = post.id;


    public static final SqlColumn<Integer> authId = post.authId;


    public static final SqlColumn<Integer> userId = post.userId;


    public static final SqlColumn<String> title = post.title;


    public static final SqlColumn<String> content = post.content;


    public static final SqlColumn<Integer> likeNum = post.likeNum;


    public static final SqlColumn<Integer> commentNum = post.commentNum;


    public static final SqlColumn<Date> createT = post.createT;


    public static final SqlColumn<Date> lastModifiedT = post.lastModifiedT;


    public static final SqlColumn<Integer> anchorId = post.anchorId;


    public static final SqlColumn<Integer> addrX = post.addrX;


    public static final SqlColumn<Integer> addrY = post.addrY;

    
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