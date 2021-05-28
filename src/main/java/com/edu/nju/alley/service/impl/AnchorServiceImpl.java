package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.constant.Constant;
import com.edu.nju.alley.dao.PostAuthMapper;
import com.edu.nju.alley.dao.PostCommentRelMapper;
import com.edu.nju.alley.dao.PostMapper;
import com.edu.nju.alley.dao.support.PostCommentRelDSS;
import com.edu.nju.alley.dao.support.PostDSS;
import com.edu.nju.alley.po.Post;
import com.edu.nju.alley.po.PostAuth;
import com.edu.nju.alley.po.PostCommentRel;
import com.edu.nju.alley.service.AnchorService;
import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.PostVO;
import com.edu.nju.alley.vo.ResponseVO;
import com.github.pagehelper.PageHelper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnchorServiceImpl implements AnchorService {

    @Resource
    PostMapper postMapper;

    @Resource
    PostCommentRelMapper postCommentRelMapper;

    @Resource
    PostAuthMapper postAuthMapper;

    @Resource
    CommentServiceImpl commentService;


    @Override
    public ResponseVO getAllPosts(Integer anchorId,
                                  Integer pageId,
                                  Integer sort,
                                  Integer label) {
        PageHelper.startPage(pageId, Constant.pageSize);
        List<Post> Posts;
        if (sort == 1)//按照时间排序
            Posts = postMapper.select(c -> c.where(PostDSS.anchorId, SqlBuilder.isEqualTo(anchorId)).and(PostDSS.labelId, SqlBuilder.isEqualTo(label)).orderBy(PostDSS.createT));
        else if (sort == 2)//按照热度排序
            Posts = postMapper.select(c -> c.where(PostDSS.anchorId, SqlBuilder.isEqualTo(anchorId)).and(PostDSS.labelId, SqlBuilder.isEqualTo(label)).orderBy(PostDSS.likeNum));
        else
            return ResponseVO.failure();
        List<PostVO> ret = new ArrayList<PostVO>();
        for (Post post : Posts) {
            //把每个Post都封装成PostVO加入列表中
            //先找到所有评论
            List<PostCommentRel> postCommentRels = postCommentRelMapper.select(c -> c.where(PostCommentRelDSS.postId, SqlBuilder.isEqualTo(post.getId())));
            List<CommentVO> commentVOS = new ArrayList<CommentVO>();
            for (PostCommentRel postCommentRel : postCommentRels) {
                commentVOS.add(commentService.getComment(postCommentRel.getCommentId()));
            }
            //找到权限
            Optional<PostAuth> postAuth = postAuthMapper.selectByPrimaryKey(post.getId());
            ret.add(new PostVO(post, commentVOS, postAuth.get()));

        }
        ResponseVO res = ResponseVO.success();
        res.add(ret);
        return res;
    }
}
