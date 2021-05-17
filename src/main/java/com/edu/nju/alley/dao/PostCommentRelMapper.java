package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.PostCommentRel;
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

import static com.edu.nju.alley.dao.support.PostCommentRelDSS.*;

@Mapper
public interface PostCommentRelMapper {

    BasicColumn[] selectList = BasicColumn.columnList(postId, commentId);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<PostCommentRel> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<PostCommentRel> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "PostCommentRelResult", value = {
            @Result(column = "post_id", property = "postId", jdbcType = JdbcType.INTEGER),
            @Result(column = "comment_id", property = "commentId", jdbcType = JdbcType.INTEGER)
    })
    Optional<PostCommentRel> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("PostCommentRelResult")
    List<PostCommentRel> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, postCommentRel, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, postCommentRel, completer);
    }


    default int insert(PostCommentRel record) {
        return MyBatis3Utils.insert(this::insert, record, postCommentRel, c ->
                c.map(postId).toProperty("postId")
                        .map(commentId).toProperty("commentId")
        );
    }


    default int insertMultiple(Collection<PostCommentRel> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, postCommentRel, c ->
                c.map(postId).toProperty("postId")
                        .map(commentId).toProperty("commentId")
        );
    }


    default int insertSelective(PostCommentRel record) {
        return MyBatis3Utils.insert(this::insert, record, postCommentRel, c ->
                c.map(postId).toPropertyWhenPresent("postId", record::getPostId)
                        .map(commentId).toPropertyWhenPresent("commentId", record::getCommentId)
        );
    }


    default Optional<PostCommentRel> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, postCommentRel, completer);
    }


    default List<PostCommentRel> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, postCommentRel, completer);
    }


    default List<PostCommentRel> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, postCommentRel, completer);
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, postCommentRel, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(PostCommentRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(postId).equalTo(record::getPostId)
                .set(commentId).equalTo(record::getCommentId);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(PostCommentRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(postId).equalToWhenPresent(record::getPostId)
                .set(commentId).equalToWhenPresent(record::getCommentId);
    }
}