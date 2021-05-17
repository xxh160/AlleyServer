package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.UserLikeComment;
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

import static com.edu.nju.alley.dao.support.UserLikeCommentDSS.*;

@Mapper
public interface UserLikeCommentMapper {

    BasicColumn[] selectList = BasicColumn.columnList(userId, commentId);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<UserLikeComment> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserLikeComment> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserLikeCommentResult", value = {
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "comment_id", property = "commentId", jdbcType = JdbcType.INTEGER)
    })
    Optional<UserLikeComment> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserLikeCommentResult")
    List<UserLikeComment> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userLikeComment, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userLikeComment, completer);
    }


    default int insert(UserLikeComment record) {
        return MyBatis3Utils.insert(this::insert, record, userLikeComment, c ->
                c.map(userId).toProperty("userId")
                        .map(commentId).toProperty("commentId")
        );
    }


    default int insertMultiple(Collection<UserLikeComment> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userLikeComment, c ->
                c.map(userId).toProperty("userId")
                        .map(commentId).toProperty("commentId")
        );
    }


    default int insertSelective(UserLikeComment record) {
        return MyBatis3Utils.insert(this::insert, record, userLikeComment, c ->
                c.map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(commentId).toPropertyWhenPresent("commentId", record::getCommentId)
        );
    }


    default Optional<UserLikeComment> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userLikeComment, completer);
    }


    default List<UserLikeComment> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userLikeComment, completer);
    }


    default List<UserLikeComment> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userLikeComment, completer);
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userLikeComment, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(UserLikeComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId)
                .set(commentId).equalTo(record::getCommentId);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserLikeComment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId)
                .set(commentId).equalToWhenPresent(record::getCommentId);
    }
}