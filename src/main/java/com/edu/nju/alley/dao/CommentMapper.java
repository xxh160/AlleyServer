package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.Comment;
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

import static com.edu.nju.alley.dao.support.CommentDSS.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface CommentMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, userId, upperId, upperTypeId, content, likeNum, createT, lastModifiedT);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Integer.class)
    int insert(InsertStatementProvider<Comment> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Comment> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "CommentResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "upper_id", property = "upperId", jdbcType = JdbcType.INTEGER),
            @Result(column = "upper_type_id", property = "upperTypeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "like_num", property = "likeNum", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_t", property = "createT", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_modified_t", property = "lastModifiedT", jdbcType = JdbcType.TIMESTAMP)
    })
    Optional<Comment> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("CommentResult")
    List<Comment> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, comment, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, comment, completer);
    }


    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int insert(Comment record) {
        return MyBatis3Utils.insert(this::insert, record, comment, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(upperId).toProperty("upperId")
                        .map(upperTypeId).toProperty("upperTypeId")
                        .map(content).toProperty("content")
                        .map(likeNum).toProperty("likeNum")
                        .map(createT).toProperty("createT")
                        .map(lastModifiedT).toProperty("lastModifiedT")
        );
    }


    default int insertMultiple(Collection<Comment> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, comment, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(upperId).toProperty("upperId")
                        .map(upperTypeId).toProperty("upperTypeId")
                        .map(content).toProperty("content")
                        .map(likeNum).toProperty("likeNum")
                        .map(createT).toProperty("createT")
                        .map(lastModifiedT).toProperty("lastModifiedT")
        );
    }


    default int insertSelective(Comment record) {
        return MyBatis3Utils.insert(this::insert, record, comment, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(upperId).toPropertyWhenPresent("upperId", record::getUpperId)
                        .map(upperTypeId).toPropertyWhenPresent("upperTypeId", record::getUpperTypeId)
                        .map(content).toPropertyWhenPresent("content", record::getContent)
                        .map(likeNum).toPropertyWhenPresent("likeNum", record::getLikeNum)
                        .map(createT).toPropertyWhenPresent("createT", record::getCreateT)
                        .map(lastModifiedT).toPropertyWhenPresent("lastModifiedT", record::getLastModifiedT)
        );
    }


    default Optional<Comment> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, comment, completer);
    }


    default List<Comment> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, comment, completer);
    }


    default List<Comment> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, comment, completer);
    }


    default Optional<Comment> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, comment, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(Comment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(upperId).equalTo(record::getUpperId)
                .set(upperTypeId).equalTo(record::getUpperTypeId)
                .set(content).equalTo(record::getContent)
                .set(likeNum).equalTo(record::getLikeNum)
                .set(createT).equalTo(record::getCreateT)
                .set(lastModifiedT).equalTo(record::getLastModifiedT);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(Comment record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(upperId).equalToWhenPresent(record::getUpperId)
                .set(upperTypeId).equalToWhenPresent(record::getUpperTypeId)
                .set(content).equalToWhenPresent(record::getContent)
                .set(likeNum).equalToWhenPresent(record::getLikeNum)
                .set(createT).equalToWhenPresent(record::getCreateT)
                .set(lastModifiedT).equalToWhenPresent(record::getLastModifiedT);
    }


    default int updateByPrimaryKey(Comment record) {
        return update(c ->
                c.set(userId).equalTo(record::getUserId)
                        .set(upperId).equalTo(record::getUpperId)
                        .set(upperTypeId).equalTo(record::getUpperTypeId)
                        .set(content).equalTo(record::getContent)
                        .set(likeNum).equalTo(record::getLikeNum)
                        .set(createT).equalTo(record::getCreateT)
                        .set(lastModifiedT).equalTo(record::getLastModifiedT)
                        .where(id, isEqualTo(record::getId))
        );
    }


    default int updateByPrimaryKeySelective(Comment record) {
        return update(c ->
                c.set(userId).equalToWhenPresent(record::getUserId)
                        .set(upperId).equalToWhenPresent(record::getUpperId)
                        .set(upperTypeId).equalToWhenPresent(record::getUpperTypeId)
                        .set(content).equalToWhenPresent(record::getContent)
                        .set(likeNum).equalToWhenPresent(record::getLikeNum)
                        .set(createT).equalToWhenPresent(record::getCreateT)
                        .set(lastModifiedT).equalToWhenPresent(record::getLastModifiedT)
                        .where(id, isEqualTo(record::getId))
        );
    }
}