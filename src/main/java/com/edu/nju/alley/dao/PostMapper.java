package com.edu.nju.alley.dao;

import static com.edu.nju.alley.dao.PostDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.edu.nju.alley.po.Post;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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

@Mapper
public interface PostMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, label, title, content, likeNum, commentNum, time, isPublic, addrX, addrY);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Post> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Post> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PostResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="label", property="label", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="like_num", property="likeNum", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_num", property="commentNum", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.DATE),
        @Result(column="is_public", property="isPublic", jdbcType=JdbcType.BIT),
        @Result(column="addr_x", property="addrX", jdbcType=JdbcType.INTEGER),
        @Result(column="addr_y", property="addrY", jdbcType=JdbcType.INTEGER)
    })
    Optional<Post> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PostResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="label", property="label", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="like_num", property="likeNum", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_num", property="commentNum", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.DATE),
        @Result(column="is_public", property="isPublic", jdbcType=JdbcType.BIT),
        @Result(column="addr_x", property="addrX", jdbcType=JdbcType.INTEGER),
        @Result(column="addr_y", property="addrY", jdbcType=JdbcType.INTEGER)
    })
    List<Post> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, post, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, post, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Post record) {
        return MyBatis3Utils.insert(this::insert, record, post, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(label).toProperty("label")
            .map(title).toProperty("title")
            .map(content).toProperty("content")
            .map(likeNum).toProperty("likeNum")
            .map(commentNum).toProperty("commentNum")
            .map(time).toProperty("time")
            .map(isPublic).toProperty("isPublic")
            .map(addrX).toProperty("addrX")
            .map(addrY).toProperty("addrY")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Post> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, post, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(label).toProperty("label")
            .map(title).toProperty("title")
            .map(content).toProperty("content")
            .map(likeNum).toProperty("likeNum")
            .map(commentNum).toProperty("commentNum")
            .map(time).toProperty("time")
            .map(isPublic).toProperty("isPublic")
            .map(addrX).toProperty("addrX")
            .map(addrY).toProperty("addrY")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Post record) {
        return MyBatis3Utils.insert(this::insert, record, post, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(label).toPropertyWhenPresent("label", record::getLabel)
            .map(title).toPropertyWhenPresent("title", record::getTitle)
            .map(content).toPropertyWhenPresent("content", record::getContent)
            .map(likeNum).toPropertyWhenPresent("likeNum", record::getLikeNum)
            .map(commentNum).toPropertyWhenPresent("commentNum", record::getCommentNum)
            .map(time).toPropertyWhenPresent("time", record::getTime)
            .map(isPublic).toPropertyWhenPresent("isPublic", record::getIsPublic)
            .map(addrX).toPropertyWhenPresent("addrX", record::getAddrX)
            .map(addrY).toPropertyWhenPresent("addrY", record::getAddrY)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Post> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, post, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Post> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, post, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Post> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, post, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Post> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, post, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Post record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(label).equalTo(record::getLabel)
                .set(title).equalTo(record::getTitle)
                .set(content).equalTo(record::getContent)
                .set(likeNum).equalTo(record::getLikeNum)
                .set(commentNum).equalTo(record::getCommentNum)
                .set(time).equalTo(record::getTime)
                .set(isPublic).equalTo(record::getIsPublic)
                .set(addrX).equalTo(record::getAddrX)
                .set(addrY).equalTo(record::getAddrY);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Post record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(label).equalToWhenPresent(record::getLabel)
                .set(title).equalToWhenPresent(record::getTitle)
                .set(content).equalToWhenPresent(record::getContent)
                .set(likeNum).equalToWhenPresent(record::getLikeNum)
                .set(commentNum).equalToWhenPresent(record::getCommentNum)
                .set(time).equalToWhenPresent(record::getTime)
                .set(isPublic).equalToWhenPresent(record::getIsPublic)
                .set(addrX).equalToWhenPresent(record::getAddrX)
                .set(addrY).equalToWhenPresent(record::getAddrY);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Post record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(label).equalTo(record::getLabel)
            .set(title).equalTo(record::getTitle)
            .set(content).equalTo(record::getContent)
            .set(likeNum).equalTo(record::getLikeNum)
            .set(commentNum).equalTo(record::getCommentNum)
            .set(time).equalTo(record::getTime)
            .set(isPublic).equalTo(record::getIsPublic)
            .set(addrX).equalTo(record::getAddrX)
            .set(addrY).equalTo(record::getAddrY)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Post record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(label).equalToWhenPresent(record::getLabel)
            .set(title).equalToWhenPresent(record::getTitle)
            .set(content).equalToWhenPresent(record::getContent)
            .set(likeNum).equalToWhenPresent(record::getLikeNum)
            .set(commentNum).equalToWhenPresent(record::getCommentNum)
            .set(time).equalToWhenPresent(record::getTime)
            .set(isPublic).equalToWhenPresent(record::getIsPublic)
            .set(addrX).equalToWhenPresent(record::getAddrX)
            .set(addrY).equalToWhenPresent(record::getAddrY)
            .where(id, isEqualTo(record::getId))
        );
    }
}