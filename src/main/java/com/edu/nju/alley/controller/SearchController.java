package com.edu.nju.alley.controller;

import com.edu.nju.alley.service.AnchorService;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.service.UserService;
import com.edu.nju.alley.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final AnchorService anchorService;

    private final PostService postService;

    private final UserService userService;

    @Autowired
    public SearchController(AnchorService anchorService,
                            PostService postService,
                            UserService userService) {
        this.anchorService = anchorService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/search")
    public ResponseVO search(@RequestParam("keywords") String keywords) {
        return ResponseVO.success().add(Arrays.asList("a", "b"));
    }

}
