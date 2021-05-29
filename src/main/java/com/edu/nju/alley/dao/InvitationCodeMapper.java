package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.InvitationCode;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.edu.nju.alley.dao.support.InvitationCodeDSS.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface InvitationCodeMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, code, userId, description);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Integer.class)
    int insert(InsertStatementProvider<InvitationCode> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<InvitationCode> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "InvitationCodeResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "code", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR)
    })
    Optional<InvitationCode> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("InvitationCodeResult")
    List<InvitationCode> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, invitationCode, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, invitationCode, completer);
    }


    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int insert(InvitationCode record) {
        return MyBatis3Utils.insert(this::insert, record, invitationCode, c ->
                c.map(id).toProperty("id")
                        .map(code).toProperty("code")
                        .map(userId).toProperty("userId")
                        .map(description).toProperty("description")
        );
    }


    default int insertMultiple(Collection<InvitationCode> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, invitationCode, c ->
                c.map(id).toProperty("id")
                        .map(code).toProperty("code")
                        .map(userId).toProperty("userId")
                        .map(description).toProperty("description")
        );
    }


    default int insertSelective(InvitationCode record) {
        return MyBatis3Utils.insert(this::insert, record, invitationCode, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(code).toPropertyWhenPresent("code", record::getCode)
                        .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(description).toPropertyWhenPresent("description", record::getDescription)
        );
    }


    default Optional<InvitationCode> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, invitationCode, completer);
    }


    default List<InvitationCode> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, invitationCode, completer);
    }


    default List<InvitationCode> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, invitationCode, completer);
    }


    default Optional<InvitationCode> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, invitationCode, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(InvitationCode record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(code).equalTo(record::getCode)
                .set(userId).equalTo(record::getUserId)
                .set(description).equalTo(record::getDescription);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(InvitationCode record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(code).equalToWhenPresent(record::getCode)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(description).equalToWhenPresent(record::getDescription);
    }


    default int updateByPrimaryKey(InvitationCode record) {
        return update(c ->
                c.set(code).equalTo(record::getCode)
                        .set(userId).equalTo(record::getUserId)
                        .set(description).equalTo(record::getDescription)
                        .where(id, isEqualTo(record::getId))
        );
    }


    default int updateByPrimaryKeySelective(InvitationCode record) {
        return update(c ->
                c.set(code).equalToWhenPresent(record::getCode)
                        .set(userId).equalToWhenPresent(record::getUserId)
                        .set(description).equalToWhenPresent(record::getDescription)
                        .where(id, isEqualTo(record::getId))
        );
    }
}