package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class AnchorDSS {

    public static final Anchor anchor = new Anchor();


    public static final SqlColumn<Integer> id = anchor.id;


    public static final SqlColumn<String> name = anchor.name;


    public static final SqlColumn<Integer> addrX = anchor.addrX;


    public static final SqlColumn<Integer> addrY = anchor.addrY;


    public static final class Anchor extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> addrX = column("addr_x", JDBCType.INTEGER);

        public final SqlColumn<Integer> addrY = column("addr_y", JDBCType.INTEGER);

        public Anchor() {
            super("anchor");
        }
    }
}