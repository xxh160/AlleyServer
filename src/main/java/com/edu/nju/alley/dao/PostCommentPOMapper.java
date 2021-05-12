package com.edu.nju.alley.dao;

import com.edu.nju.alley.po.PostCommentPO;
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

import static com.edu.nju.alley.dao.support.PostCommentPODynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface PostCommentPOMapper {

    BasicColumn[] selectList = BasicColumn.columnList(id, userId, postId, content, likeNum);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<PostCommentPO> insertStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<PostCommentPO> multipleInsertStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "PostCommentPOResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "post_id", property = "postId", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.CHAR),
            @Result(column = "like_num", property = "likeNum", jdbcType = JdbcType.INTEGER)
    })
    Optional<PostCommentPO> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "PostCommentPOResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
            @Result(column = "post_id", property = "postId", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.CHAR),
            @Result(column = "like_num", property = "likeNum", jdbcType = JdbcType.INTEGER)
    })
    List<PostCommentPO> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, postCommentPO, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, postCommentPO, completer);
    }

    default int deleteByPrimaryKey(Integer id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    default int insert(PostCommentPO record) {
        return MyBatis3Utils.insert(this::insert, record, postCommentPO, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(postId).toProperty("postId")
                        .map(content).toProperty("content")
                        .map(likeNum).toProperty("likeNum")
        );
    }

    default int insertMultiple(Collection<PostCommentPO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, postCommentPO, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(postId).toProperty("postId")
                        .map(content).toProperty("content")
                        .map(likeNum).toProperty("likeNum")
        );
    }

    default int insertSelective(PostCommentPO record) {
        return MyBatis3Utils.insert(this::insert, record, postCommentPO, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(postId).toPropertyWhenPresent("postId", record::getPostId)
                        .map(content).toPropertyWhenPresent("content", record::getContent)
                        .map(likeNum).toPropertyWhenPresent("likeNum", record::getLikeNum)
        );
    }

    default Optional<PostCommentPO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, postCommentPO, completer);
    }

    default List<PostCommentPO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, postCommentPO, completer);
    }

    default List<PostCommentPO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, postCommentPO, completer);
    }

    default Optional<PostCommentPO> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }


    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, postCommentPO, completer);
    }


    static UpdateDSL<UpdateModel> updateAllColumns(PostCommentPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(postId).equalTo(record::getPostId)
                .set(content).equalTo(record::getContent)
                .set(likeNum).equalTo(record::getLikeNum);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(PostCommentPO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(postId).equalToWhenPresent(record::getPostId)
                .set(content).equalToWhenPresent(record::getContent)
                .set(likeNum).equalToWhenPresent(record::getLikeNum);
    }

    default int updateByPrimaryKey(PostCommentPO record) {
        return update(c ->
                c.set(userId).equalTo(record::getUserId)
                        .set(postId).equalTo(record::getPostId)
                        .set(content).equalTo(record::getContent)
                        .set(likeNum).equalTo(record::getLikeNum)
                        .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(PostCommentPO record) {
        return update(c ->
                c.set(userId).equalToWhenPresent(record::getUserId)
                        .set(postId).equalToWhenPresent(record::getPostId)
                        .set(content).equalToWhenPresent(record::getContent)
                        .set(likeNum).equalToWhenPresent(record::getLikeNum)
                        .where(id, isEqualTo(record::getId))
        );
    }
}