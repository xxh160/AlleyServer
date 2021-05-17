package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class AnchorDSS {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Anchor anchor = new Anchor();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = anchor.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = anchor.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> addrX = anchor.addrX;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> addrY = anchor.addrY;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
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