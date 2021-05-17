package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.UserPostRel;
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

import static com.edu.nju.alley.dao.support.UserPostRelDSS.*;

@Mapper
public interface UserPostRelMapper {

    BasicColumn[] selectList = BasicColumn.columnList(userId, postId);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<UserPostRel> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserPostRel> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserPostRelResult", value = {
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "post_id", property = "postId", jdbcType = JdbcType.INTEGER)
    })
    Optional<UserPostRel> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserPostRelResult")
    List<UserPostRel> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userPostRel, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userPostRel, completer);
    }


    default int insert(UserPostRel record) {
        return MyBatis3Utils.insert(this::insert, record, userPostRel, c ->
                c.map(userId).toProperty("userId")
                        .map(postId).toProperty("postId")
        );
    }


    default int insertMultiple(Collection<UserPostRel> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userPostRel, c ->
                c.map(userId).toProperty("userId")
                        .map(postId).toProperty("postId")
        );
    }


    default int insertSelective(UserPostRel record) {
        return MyBatis3Utils.insert(this::insert, record, userPostRel, c ->
                c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(postId).toPropertyWhenPresent("postId", record::getPostId)
        );
    }


    default Optional<UserPostRel> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userPostRel, completer);
    }


    default List<UserPostRel> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userPostRel, completer);
    }


    default List<UserPostRel> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userPostRel, completer);
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userPostRel, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(UserPostRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(postId).equalTo(record::getPostId);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserPostRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(postId).equalToWhenPresent(record::getPostId);
    }
}