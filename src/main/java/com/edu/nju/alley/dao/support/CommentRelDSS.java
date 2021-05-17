package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class CommentRelDSS {

    public static final CommentRel commentRel = new CommentRel();


    public static final SqlColumn<Integer> fatherId = commentRel.fatherId;


    public static final SqlColumn<Integer> childId = commentRel.childId;

    
    public static final class CommentRel extends SqlTable {
        public final SqlColumn<Integer> fatherId = column("father_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> childId = column("child_id", JDBCType.INTEGER);

        public CommentRel() {
            super("comment_rel");
        }
    }
}