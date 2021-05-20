package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.UserAuth;
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

import static com.edu.nju.alley.dao.support.UserAuthDSS.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface UserAuthMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, chat, position, makeFriend, showWxInfo, official);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<UserAuth> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserAuth> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserAuthResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "chat", property = "chat", jdbcType = JdbcType.BIT),
            @Result(column = "position", property = "position", jdbcType = JdbcType.BIT),
            @Result(column = "make_friend", property = "makeFriend", jdbcType = JdbcType.BIT),
            @Result(column = "show_wx_info", property = "showWxInfo", jdbcType = JdbcType.BIT),
            @Result(column = "official", property = "official", jdbcType = JdbcType.BIT)
    })
    Optional<UserAuth> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserAuthResult")
    List<UserAuth> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userAuth, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userAuth, completer);
    }


    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int insert(UserAuth record) {
        return MyBatis3Utils.insert(this::insert, record, userAuth, c ->
                c.map(id).toProperty("id")
                        .map(chat).toProperty("chat")
                        .map(position).toProperty("position")
                        .map(makeFriend).toProperty("makeFriend")
                        .map(showWxInfo).toProperty("showWxInfo")
                        .map(official).toProperty("official")
        );
    }


    default int insertMultiple(Collection<UserAuth> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userAuth, c ->
                c.map(id).toProperty("id")
                        .map(chat).toProperty("chat")
                        .map(position).toProperty("position")
                        .map(makeFriend).toProperty("makeFriend")
                        .map(showWxInfo).toProperty("showWxInfo")
                        .map(official).toProperty("official")
        );
    }


    default int insertSelective(UserAuth record) {
        return MyBatis3Utils.insert(this::insert, record, userAuth, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(chat).toPropertyWhenPresent("chat", record::getChat)
                        .map(position).toPropertyWhenPresent("position", record::getPosition)
                        .map(makeFriend).toPropertyWhenPresent("makeFriend", record::getMakeFriend)
                        .map(showWxInfo).toPropertyWhenPresent("showWxInfo", record::getShowWxInfo)
                        .map(official).toPropertyWhenPresent("official", record::getOfficial)
        );
    }


    default Optional<UserAuth> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userAuth, completer);
    }


    default List<UserAuth> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userAuth, completer);
    }


    default List<UserAuth> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userAuth, completer);
    }


    default Optional<UserAuth> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userAuth, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(UserAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(chat).equalTo(record::getChat)
                .set(position).equalTo(record::getPosition)
                .set(makeFriend).equalTo(record::getMakeFriend)
                .set(showWxInfo).equalTo(record::getShowWxInfo)
                .set(official).equalTo(record::getOfficial);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(chat).equalToWhenPresent(record::getChat)
                .set(position).equalToWhenPresent(record::getPosition)
                .set(makeFriend).equalToWhenPresent(record::getMakeFriend)
                .set(showWxInfo).equalToWhenPresent(record::getShowWxInfo)
                .set(official).equalToWhenPresent(record::getOfficial);
    }


    default int updateByPrimaryKey(UserAuth record) {
        return update(c ->
                c.set(chat).equalTo(record::getChat)
                        .set(position).equalTo(record::getPosition)
                        .set(makeFriend).equalTo(record::getMakeFriend)
                        .set(showWxInfo).equalTo(record::getShowWxInfo)
                        .set(official).equalTo(record::getOfficial)
                        .where(id, isEqualTo(record::getId))
        );
    }


    default int updateByPrimaryKeySelective(UserAuth record) {
        return update(c ->
                c.set(chat).equalToWhenPresent(record::getChat)
                        .set(position).equalToWhenPresent(record::getPosition)
                        .set(makeFriend).equalToWhenPresent(record::getMakeFriend)
                        .set(showWxInfo).equalToWhenPresent(record::getShowWxInfo)
                        .set(official).equalToWhenPresent(record::getOfficial)
                        .where(id, isEqualTo(record::getId))
        );
    }
}