package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class LabelDSS {

    public static final Label label = new Label();


    public static final SqlColumn<Integer> id = label.id;


    public static final SqlColumn<String> name = label.name;


    public static final class Label extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public Label() {
            super("label");
        }
    }
}