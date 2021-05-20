package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.Post;
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

import static com.edu.nju.alley.dao.support.PostDSS.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface PostMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, authId, userId, labelId, title, content, likeNum, commentNum, createT, lastModifiedT, anchorId, addrX, addrY);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);


    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Post> insertStatement);


    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Post> multipleInsertStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "PostResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "auth_id", property = "authId", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "label_id", property = "labelId", jdbcType = JdbcType.INTEGER),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "like_num", property = "likeNum", jdbcType = JdbcType.INTEGER),
            @Result(column = "comment_num", property = "commentNum", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_t", property = "createT", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_modified_t", property = "lastModifiedT", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "anchor_id", property = "anchorId", jdbcType = JdbcType.INTEGER),
            @Result(column = "addr_x", property = "addrX", jdbcType = JdbcType.INTEGER),
            @Result(column = "addr_y", property = "addrY", jdbcType = JdbcType.INTEGER)
    })
    Optional<Post> selectOne(SelectStatementProvider selectStatement);


    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("PostResult")
    List<Post> selectMany(SelectStatementProvider selectStatement);


    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);


    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, post, completer);
    }


    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, post, completer);
    }


    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int insert(Post record) {
        return MyBatis3Utils.insert(this::insert, record, post, c ->
                c.map(id).toProperty("id")
                        .map(authId).toProperty("authId")
                        .map(userId).toProperty("userId")
                        .map(labelId).toProperty("labelId")
                        .map(title).toProperty("title")
                        .map(content).toProperty("content")
                        .map(likeNum).toProperty("likeNum")
                        .map(commentNum).toProperty("commentNum")
                        .map(createT).toProperty("createT")
                        .map(lastModifiedT).toProperty("lastModifiedT")
                        .map(anchorId).toProperty("anchorId")
                        .map(addrX).toProperty("addrX")
                        .map(addrY).toProperty("addrY")
        );
    }


    default int insertMultiple(Collection<Post> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, post, c ->
                c.map(id).toProperty("id")
                        .map(authId).toProperty("authId")
                        .map(userId).toProperty("userId")
                        .map(labelId).toProperty("labelId")
                        .map(title).toProperty("title")
                        .map(content).toProperty("content")
                        .map(likeNum).toProperty("likeNum")
                        .map(commentNum).toProperty("commentNum")
                        .map(createT).toProperty("createT")
                        .map(lastModifiedT).toProperty("lastModifiedT")
                        .map(anchorId).toProperty("anchorId")
                        .map(addrX).toProperty("addrX")
                        .map(addrY).toProperty("addrY")
        );
    }


    default int insertSelective(Post record) {
        return MyBatis3Utils.insert(this::insert, record, post, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(authId).toPropertyWhenPresent("authId", record::getAuthId)
                        .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(labelId).toPropertyWhenPresent("labelId", record::getLabelId)
                        .map(title).toPropertyWhenPresent("title", record::getTitle)
                        .map(content).toPropertyWhenPresent("content", record::getContent)
                        .map(likeNum).toPropertyWhenPresent("likeNum", record::getLikeNum)
                        .map(commentNum).toPropertyWhenPresent("commentNum", record::getCommentNum)
                        .map(createT).toPropertyWhenPresent("createT", record::getCreateT)
                        .map(lastModifiedT).toPropertyWhenPresent("lastModifiedT", record::getLastModifiedT)
                        .map(anchorId).toPropertyWhenPresent("anchorId", record::getAnchorId)
                        .map(addrX).toPropertyWhenPresent("addrX", record::getAddrX)
                        .map(addrY).toPropertyWhenPresent("addrY", record::getAddrY)
        );
    }


    default Optional<Post> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, post, completer);
    }


    default List<Post> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, post, completer);
    }


    default List<Post> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, post, completer);
    }


    default Optional<Post> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, post, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(Post record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(authId).equalTo(record::getAuthId)
                .set(userId).equalTo(record::getUserId)
                .set(labelId).equalTo(record::getLabelId)
                .set(title).equalTo(record::getTitle)
                .set(content).equalTo(record::getContent)
                .set(likeNum).equalTo(record::getLikeNum)
                .set(commentNum).equalTo(record::getCommentNum)
                .set(createT).equalTo(record::getCreateT)
                .set(lastModifiedT).equalTo(record::getLastModifiedT)
                .set(anchorId).equalTo(record::getAnchorId)
                .set(addrX).equalTo(record::getAddrX)
                .set(addrY).equalTo(record::getAddrY);
    }


    static UpdateDSL<UpdateModel> updateSelectiveColumns(Post record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(authId).equalToWhenPresent(record::getAuthId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(labelId).equalToWhenPresent(record::getLabelId)
                .set(title).equalToWhenPresent(record::getTitle)
                .set(content).equalToWhenPresent(record::getContent)
                .set(likeNum).equalToWhenPresent(record::getLikeNum)
                .set(commentNum).equalToWhenPresent(record::getCommentNum)
                .set(createT).equalToWhenPresent(record::getCreateT)
                .set(lastModifiedT).equalToWhenPresent(record::getLastModifiedT)
                .set(anchorId).equalToWhenPresent(record::getAnchorId)
                .set(addrX).equalToWhenPresent(record::getAddrX)
                .set(addrY).equalToWhenPresent(record::getAddrY);
    }


    default int updateByPrimaryKey(Post record) {
        return update(c ->
                c.set(authId).equalTo(record::getAuthId)
                        .set(userId).equalTo(record::getUserId)
                        .set(labelId).equalTo(record::getLabelId)
                        .set(title).equalTo(record::getTitle)
                        .set(content).equalTo(record::getContent)
                        .set(likeNum).equalTo(record::getLikeNum)
                        .set(commentNum).equalTo(record::getCommentNum)
                        .set(createT).equalTo(record::getCreateT)
                        .set(lastModifiedT).equalTo(record::getLastModifiedT)
                        .set(anchorId).equalTo(record::getAnchorId)
                        .set(addrX).equalTo(record::getAddrX)
                        .set(addrY).equalTo(record::getAddrY)
                        .where(id, isEqualTo(record::getId))
        );
    }


    default int updateByPrimaryKeySelective(Post record) {
        return update(c ->
                c.set(authId).equalToWhenPresent(record::getAuthId)
                        .set(userId).equalToWhenPresent(record::getUserId)
                        .set(labelId).equalToWhenPresent(record::getLabelId)
                        .set(title).equalToWhenPresent(record::getTitle)
                        .set(content).equalToWhenPresent(record::getContent)
                        .set(likeNum).equalToWhenPresent(record::getLikeNum)
                        .set(commentNum).equalToWhenPresent(record::getCommentNum)
                        .set(createT).equalToWhenPresent(record::getCreateT)
                        .set(lastModifiedT).equalToWhenPresent(record::getLastModifiedT)
                        .set(anchorId).equalToWhenPresent(record::getAnchorId)
                        .set(addrX).equalToWhenPresent(record::getAddrX)
                        .set(addrY).equalToWhenPresent(record::getAddrY)
                        .where(id, isEqualTo(record::getId))
        );
    }
}