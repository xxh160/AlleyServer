package com.edu.nju.alley.service;

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

}
