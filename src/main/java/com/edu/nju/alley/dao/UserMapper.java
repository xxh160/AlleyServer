package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.User;
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

import static com.edu.nju.alley.dao.support.UserDSS.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface UserMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, authId, sign, openid, gender, name, avatar);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Integer.class)
    int insert(InsertStatementProvider<User> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<User> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "auth_id", property = "authId", jdbcType = JdbcType.INTEGER),
            @Result(column = "sign", property = "sign", jdbcType = JdbcType.VARCHAR),
            @Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.INTEGER),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar", property = "avatar", jdbcType = JdbcType.VARCHAR)
    })
    Optional<User> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserResult")
    List<User> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, user, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, user, completer);
    }


    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int insert(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
                c.map(id).toProperty("id")
                        .map(authId).toProperty("authId")
                        .map(sign).toProperty("sign")
                        .map(openid).toProperty("openid")
                        .map(gender).toProperty("gender")
                        .map(name).toProperty("name")
                        .map(avatar).toProperty("avatar")
        );
    }


    default int insertMultiple(Collection<User> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, user, c ->
                c.map(id).toProperty("id")
                        .map(authId).toProperty("authId")
                        .map(sign).toProperty("sign")
                        .map(openid).toProperty("openid")
                        .map(gender).toProperty("gender")
                        .map(name).toProperty("name")
                        .map(avatar).toProperty("avatar")
        );
    }


    default int insertSelective(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(authId).toPropertyWhenPresent("authId", record::getAuthId)
                        .map(sign).toPropertyWhenPresent("sign", record::getSign)
                        .map(openid).toPropertyWhenPresent("openid", record::getOpenid)
                        .map(gender).toPropertyWhenPresent("gender", record::getGender)
                        .map(name).toPropertyWhenPresent("name", record::getName)
                        .map(avatar).toPropertyWhenPresent("avatar", record::getAvatar)
        );
    }


    default Optional<User> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, user, completer);
    }


    default List<User> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, user, completer);
    }


    default List<User> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, user, completer);
    }


    default Optional<User> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, user, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(authId).equalTo(record::getAuthId)
                .set(sign).equalTo(record::getSign)
                .set(openid).equalTo(record::getOpenid)
                .set(gender).equalTo(record::getGender)
                .set(name).equalTo(record::getName)
                .set(avatar).equalTo(record::getAvatar);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(authId).equalToWhenPresent(record::getAuthId)
                .set(sign).equalToWhenPresent(record::getSign)
                .set(openid).equalToWhenPresent(record::getOpenid)
                .set(gender).equalToWhenPresent(record::getGender)
                .set(name).equalToWhenPresent(record::getName)
                .set(avatar).equalToWhenPresent(record::getAvatar);
    }


    default int updateByPrimaryKey(User record) {
        return update(c ->
                c.set(authId).equalTo(record::getAuthId)
                        .set(sign).equalTo(record::getSign)
                        .set(openid).equalTo(record::getOpenid)
                        .set(gender).equalTo(record::getGender)
                        .set(name).equalTo(record::getName)
                        .set(avatar).equalTo(record::getAvatar)
                        .where(id, isEqualTo(record::getId))
        );
    }


    default int updateByPrimaryKeySelective(User record) {
        return update(c ->
                c.set(authId).equalToWhenPresent(record::getAuthId)
                        .set(sign).equalToWhenPresent(record::getSign)
                        .set(openid).equalToWhenPresent(record::getOpenid)
                        .set(gender).equalToWhenPresent(record::getGender)
                        .set(name).equalToWhenPresent(record::getName)
                        .set(avatar).equalToWhenPresent(record::getAvatar)
                        .where(id, isEqualTo(record::getId))
        );
    }
}