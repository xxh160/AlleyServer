package com.edu.nju.alley.dao.support;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public final class InvitationCodeDSS {

    public static final InvitationCode invitationCode = new InvitationCode();


    public static final SqlColumn<Integer> id = invitationCode.id;


    public static final SqlColumn<String> code = invitationCode.code;


    public static final SqlColumn<Integer> userId = invitationCode.userId;


    public static final SqlColumn<String> description = invitationCode.description;


    public static final class InvitationCode extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public InvitationCode() {
            super("invitation_code");
        }
    }
}