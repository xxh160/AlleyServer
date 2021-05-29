package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.PostAuth;
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

import static com.edu.nju.alley.dao.support.PostAuthDSS.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface PostAuthMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, visible, comment);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Integer.class)
    int insert(InsertStatementProvider<PostAuth> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<PostAuth> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "PostAuthResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "visible", property = "visible", jdbcType = JdbcType.BIT),
            @Result(column = "comment", property = "comment", jdbcType = JdbcType.BIT)
    })
    Optional<PostAuth> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("PostAuthResult")
    List<PostAuth> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, postAuth, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, postAuth, completer);
    }


    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int insert(PostAuth record) {
        return MyBatis3Utils.insert(this::insert, record, postAuth, c ->
                c.map(id).toProperty("id")
                        .map(visible).toProperty("visible")
                        .map(comment).toProperty("comment")
        );
    }


    default int insertMultiple(Collection<PostAuth> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, postAuth, c ->
                c.map(id).toProperty("id")
                        .map(visible).toProperty("visible")
                        .map(comment).toProperty("comment")
        );
    }


    default int insertSelective(PostAuth record) {
        return MyBatis3Utils.insert(this::insert, record, postAuth, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(visible).toPropertyWhenPresent("visible", record::getVisible)
                        .map(comment).toPropertyWhenPresent("comment", record::getComment)
        );
    }


    default Optional<PostAuth> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, postAuth, completer);
    }


    default List<PostAuth> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, postAuth, completer);
    }


    default List<PostAuth> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, postAuth, completer);
    }


    default Optional<PostAuth> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, postAuth, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(PostAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(visible).equalTo(record::getVisible)
                .set(comment).equalTo(record::getComment);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(PostAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(visible).equalToWhenPresent(record::getVisible)
                .set(comment).equalToWhenPresent(record::getComment);
    }


    default int updateByPrimaryKey(PostAuth record) {
        return update(c ->
                c.set(visible).equalTo(record::getVisible)
                        .set(comment).equalTo(record::getComment)
                        .where(id, isEqualTo(record::getId))
        );
    }


    default int updateByPrimaryKeySelective(PostAuth record) {
        return update(c ->
                c.set(visible).equalToWhenPresent(record::getVisible)
                        .set(comment).equalToWhenPresent(record::getComment)
                        .where(id, isEqualTo(record::getId))
        );
    }
}