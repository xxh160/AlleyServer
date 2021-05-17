package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.Anchor;
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

import static com.edu.nju.alley.dao.support.AnchorDSS.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface AnchorMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, name, addrX, addrY);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Anchor> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Anchor> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "AnchorResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "addr_x", property = "addrX", jdbcType = JdbcType.INTEGER),
            @Result(column = "addr_y", property = "addrY", jdbcType = JdbcType.INTEGER)
    })
    Optional<Anchor> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("AnchorResult")
    List<Anchor> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, anchor, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, anchor, completer);
    }


    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int insert(Anchor record) {
        return MyBatis3Utils.insert(this::insert, record, anchor, c ->
                c.map(id).toProperty("id")
                        .map(name).toProperty("name")
                        .map(addrX).toProperty("addrX")
                        .map(addrY).toProperty("addrY")
        );
    }


    default int insertMultiple(Collection<Anchor> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, anchor, c ->
                c.map(id).toProperty("id")
                        .map(name).toProperty("name")
                        .map(addrX).toProperty("addrX")
                        .map(addrY).toProperty("addrY")
        );
    }


    default int insertSelective(Anchor record) {
        return MyBatis3Utils.insert(this::insert, record, anchor, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(name).toPropertyWhenPresent("name", record::getName)
                        .map(addrX).toPropertyWhenPresent("addrX", record::getAddrX)
                        .map(addrY).toPropertyWhenPresent("addrY", record::getAddrY)
        );
    }


    default Optional<Anchor> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, anchor, completer);
    }


    default List<Anchor> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, anchor, completer);
    }


    default List<Anchor> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, anchor, completer);
    }


    default Optional<Anchor> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, anchor, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(Anchor record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(addrX).equalTo(record::getAddrX)
                .set(addrY).equalTo(record::getAddrY);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(Anchor record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(addrX).equalToWhenPresent(record::getAddrX)
                .set(addrY).equalToWhenPresent(record::getAddrY);
    }


    default int updateByPrimaryKey(Anchor record) {
        return update(c ->
                c.set(name).equalTo(record::getName)
                        .set(addrX).equalTo(record::getAddrX)
                        .set(addrY).equalTo(record::getAddrY)
                        .where(id, isEqualTo(record::getId))
        );
    }


    default int updateByPrimaryKeySelective(Anchor record) {
        return update(c ->
                c.set(name).equalToWhenPresent(record::getName)
                        .set(addrX).equalToWhenPresent(record::getAddrX)
                        .set(addrY).equalToWhenPresent(record::getAddrY)
                        .where(id, isEqualTo(record::getId))
        );
    }
}