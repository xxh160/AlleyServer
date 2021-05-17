package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.CommentRel;
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

import static com.edu.nju.alley.dao.support.CommentRelDSS.*;

@Mapper
public interface CommentRelMapper {

    BasicColumn[] selectList = BasicColumn.columnList(fatherId, childId);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<CommentRel> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<CommentRel> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "CommentRelResult", value = {
            @Result(column = "father_id", property = "fatherId", jdbcType = JdbcType.INTEGER),
            @Result(column = "child_id", property = "childId", jdbcType = JdbcType.INTEGER)
    })
    Optional<CommentRel> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("CommentRelResult")
    List<CommentRel> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, commentRel, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, commentRel, completer);
    }


    default int insert(CommentRel record) {
        return MyBatis3Utils.insert(this::insert, record, commentRel, c ->
                c.map(fatherId).toProperty("fatherId")
                        .map(childId).toProperty("childId")
        );
    }


    default int insertMultiple(Collection<CommentRel> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, commentRel, c ->
                c.map(fatherId).toProperty("fatherId")
                        .map(childId).toProperty("childId")
        );
    }


    default int insertSelective(CommentRel record) {
        return MyBatis3Utils.insert(this::insert, record, commentRel, c ->
                c.map(fatherId).toPropertyWhenPresent("fatherId", record::getFatherId)
                        .map(childId).toPropertyWhenPresent("childId", record::getChildId)
        );
    }


    default Optional<CommentRel> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, commentRel, completer);
    }


    default List<CommentRel> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, commentRel, completer);
    }


    default List<CommentRel> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, commentRel, completer);
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, commentRel, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(CommentRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(fatherId).equalTo(record::getFatherId)
                .set(childId).equalTo(record::getChildId);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(CommentRel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(fatherId).equalToWhenPresent(record::getFatherId)
                .set(childId).equalToWhenPresent(record::getChildId);
    }
}