package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class AnchorDSS {

    public static final Anchor anchor = new Anchor();


    public static final SqlColumn<Integer> id = anchor.id;


    public static final SqlColumn<String> name = anchor.name;


    public static final SqlColumn<Float> longitude = anchor.longitude;


    public static final SqlColumn<Float> latitude = anchor.latitude;


    public static final class Anchor extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Float> longitude = column("longitude", JDBCType.FLOAT);

        public final SqlColumn<Float> latitude = column("latitude", JDBCType.FLOAT);

        public Anchor() {
            super("anchor");
        }
    }
}