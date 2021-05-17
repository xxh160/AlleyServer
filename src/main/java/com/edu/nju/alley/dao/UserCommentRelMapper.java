package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.UserCommentRel;
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

import static com.edu.nju.alley.dao.support.UserCommentRelDSS.*;

@Mapper
public interface UserCommentRelMapper {

    BasicColumn[] selectList = BasicColumn.columnList(userId, commentId);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<UserCommentRel> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserCommentRel> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserCommentRelResult", value = {
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "comment_id", property = "commentId", jdbcType = JdbcType.INTEGER)
    })
    Optional<UserCommentRel> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserCommentRelResult")
    List<UserCommentRel> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userCommentRel, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userCommentRel, completer);
    }


    default int insert(UserCommentRel record) {
        return MyBatis3Utils.insert(this::insert, record, userCommentRel, c ->
                c.map(userId).toProperty("userId")
                        .map(commentId).toProperty("commentId")
        );
    }


    default int insertMultiple(Collection<UserCommentRel> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userCommentRel, c ->
                c.map(userId).toProperty("userId")
                        .map(commentId).toProperty("commentId")
        );
    }


    default int insertSelective(UserCommentRel record) {
        return MyBatis3Utils.insert(this::insert, record, userCommentRel, c ->
                c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(commentId).toPropertyWhenPresent("commentId", record::getCommentId)
        );
    }


    default Optional<UserCommentRel> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userCommentRel, completer);
    }


    default List<UserCommentRel> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userCommentRel, completer);
    }


    default List<UserCommentRel> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userCommentRel, completer);
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userCommentRel, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(UserCommentRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(commentId).equalTo(record::getCommentId);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserCommentRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(commentId).equalToWhenPresent(record::getCommentId);
    }
}