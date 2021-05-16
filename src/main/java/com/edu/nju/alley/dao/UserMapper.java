package com.edu.nju.alley.dao;

import static com.edu.nju.alley.dao.UserDynamicSqlSupport.*;

import com.edu.nju.alley.po.User;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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

@Mapper
public interface UserMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, gender, personalIntroduction, allowChat, allowFriendRequest, allowSharePosition);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<User> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="personal_introduction", property="personalIntroduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="allow_chat", property="allowChat", jdbcType=JdbcType.BIT),
        @Result(column="allow_friend_request", property="allowFriendRequest", jdbcType=JdbcType.BIT),
        @Result(column="allow_share_position", property="allowSharePosition", jdbcType=JdbcType.BIT)
    })
    Optional<User> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        @Result(column="personal_introduction", property="personalIntroduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="allow_chat", property="allowChat", jdbcType=JdbcType.BIT),
        @Result(column="allow_friend_request", property="allowFriendRequest", jdbcType=JdbcType.BIT),
        @Result(column="allow_share_position", property="allowSharePosition", jdbcType=JdbcType.BIT)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(id).toProperty("id")
            .map(gender).toProperty("gender")
            .map(personalIntroduction).toProperty("personalIntroduction")
            .map(allowChat).toProperty("allowChat")
            .map(allowFriendRequest).toProperty("allowFriendRequest")
            .map(allowSharePosition).toProperty("allowSharePosition")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<User> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, user, c ->
            c.map(id).toProperty("id")
            .map(gender).toProperty("gender")
            .map(personalIntroduction).toProperty("personalIntroduction")
            .map(allowChat).toProperty("allowChat")
            .map(allowFriendRequest).toProperty("allowFriendRequest")
            .map(allowSharePosition).toProperty("allowSharePosition")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(gender).toPropertyWhenPresent("gender", record::getGender)
            .map(personalIntroduction).toPropertyWhenPresent("personalIntroduction", record::getPersonalIntroduction)
            .map(allowChat).toPropertyWhenPresent("allowChat", record::getAllowChat)
            .map(allowFriendRequest).toPropertyWhenPresent("allowFriendRequest", record::getAllowFriendRequest)
            .map(allowSharePosition).toPropertyWhenPresent("allowSharePosition", record::getAllowSharePosition)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<User> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<User> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<User> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(gender).equalTo(record::getGender)
                .set(personalIntroduction).equalTo(record::getPersonalIntroduction)
                .set(allowChat).equalTo(record::getAllowChat)
                .set(allowFriendRequest).equalTo(record::getAllowFriendRequest)
                .set(allowSharePosition).equalTo(record::getAllowSharePosition);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(gender).equalToWhenPresent(record::getGender)
                .set(personalIntroduction).equalToWhenPresent(record::getPersonalIntroduction)
                .set(allowChat).equalToWhenPresent(record::getAllowChat)
                .set(allowFriendRequest).equalToWhenPresent(record::getAllowFriendRequest)
                .set(allowSharePosition).equalToWhenPresent(record::getAllowSharePosition);
    }
}