package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.UserLikePost;
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

import static com.edu.nju.alley.dao.support.UserLikePostDSS.*;

@Mapper
public interface UserLikePostMapper {

    BasicColumn[] selectList = BasicColumn.columnList(userId, postId);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<UserLikePost> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserLikePost> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserLikePostResult", value = {
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "post_id", property = "postId", jdbcType = JdbcType.INTEGER)
    })
    Optional<UserLikePost> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserLikePostResult")
    List<UserLikePost> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userLikePost, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userLikePost, completer);
    }


    default int insert(UserLikePost record) {
        return MyBatis3Utils.insert(this::insert, record, userLikePost, c ->
                c.map(userId).toProperty("userId")
                        .map(postId).toProperty("postId")
        );
    }


    default int insertMultiple(Collection<UserLikePost> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userLikePost, c ->
                c.map(userId).toProperty("userId")
                        .map(postId).toProperty("postId")
        );
    }


    default int insertSelective(UserLikePost record) {
        return MyBatis3Utils.insert(this::insert, record, userLikePost, c ->
                c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(postId).toPropertyWhenPresent("postId", record::getPostId)
        );
    }


    default Optional<UserLikePost> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userLikePost, completer);
    }


    default List<UserLikePost> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userLikePost, completer);
    }


    default List<UserLikePost> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userLikePost, completer);
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userLikePost, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(UserLikePost record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(postId).equalTo(record::getPostId);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserLikePost record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(postId).equalToWhenPresent(record::getPostId);
    }
}