package com.edu.nju.alley.util;

import com.edu.nju.alley.dao.CommentMapper;
import com.edu.nju.alley.dao.UserMapper;
import com.edu.nju.alley.dao.support.CommentDSS;
import com.edu.nju.alley.dao.support.UserDSS;
import com.edu.nju.alley.po.UserLikeComment;
import com.edu.nju.alley.vo.UserActionVO;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@SpringBootTest
public class PageHelperTest {

    private final CommentMapper commentMapper;

    private final UserMapper userMapper;

    @Autowired
    public PageHelperTest(CommentMapper commentMapper,
                          UserMapper userMapper) {
        this.commentMapper = commentMapper;
        this.userMapper = userMapper;
    }

    public List<UserActionVO> test(int pageId, int pageSize) {
        PageHelper.startPage(pageId, pageSize);
        int userId = 1;
        List<UserActionVO> all = new ArrayList<>();
        all.add(new UserActionVO(new UserLikeComment(3, 100)));
        commentMapper.select(c -> c.where(CommentDSS.userId, isEqualTo(userId)))
                .forEach(cur -> all.addAll(userMapper.select(c -> c.where(UserDSS.id, isEqualTo(userId)))
                        .stream()
                        .map(t -> {
                            UserLikeComment userLikeComment = new UserLikeComment(t.getId(), cur.getId());
                            return new UserActionVO(userLikeComment);
                        }).collect(Collectors.toList())));

        return all;
    }

    @Test
    public void PageTest() {
        List<UserActionVO> list = test(1, 11);
    }

}
