package com.edu.nju.alley.service;

import com.edu.nju.alley.vo.CommentVO;
import com.edu.nju.alley.vo.PostVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    private final PostService postService;

    @Autowired
    public PostServiceTest(PostService postService) {
        this.postService = postService;
    }

    @Test
    public void deletePostTest() {
        this.postService.deletePost(8);
        this.postService.deletePost(9);
    }

    @Test
    public void getSpecificPostTest() {
        PostVO postVO = this.postService.getSpecificPost(28);
        postVO.getComments().stream().map(CommentVO::getCreateTime).forEach(System.out::println);
    }

}
