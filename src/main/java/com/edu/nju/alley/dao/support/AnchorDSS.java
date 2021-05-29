package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class AnchorDSS {

    public static final Anchor anchor = new Anchor();


    public static final SqlColumn<Integer> id = anchor.id;


    public static final SqlColumn<String> name = anchor.name;


    public static final SqlColumn<Integer> longitude = anchor.longitude;


    public static final SqlColumn<Integer> latitude = anchor.latitude;


    public static final class Anchor extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> longitude = column("longitude", JDBCType.INTEGER);

        public final SqlColumn<Integer> latitude = column("latitude", JDBCType.INTEGER);

        public Anchor() {
            super("anchor");
        }
    }
}