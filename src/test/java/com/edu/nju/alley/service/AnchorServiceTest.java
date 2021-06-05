package com.edu.nju.alley.service;

import com.edu.nju.alley.enums.Labels;
import com.edu.nju.alley.enums.Sort;
import com.edu.nju.alley.vo.PostViewVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AnchorServiceTest {

    private final AnchorService anchorService;

    @Autowired
    public AnchorServiceTest(AnchorService anchorService) {
        this.anchorService = anchorService;
    }

    @Test
    public void getAllPostTest() {
        List<PostViewVO> posts = anchorService.getAllPosts(2, 1, Sort.HOT.getCode(), Labels.ALL.getCode());
        Assertions.assertEquals(posts.size(), 0);
    }

}
