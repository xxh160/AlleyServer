package com.edu.nju.alley.controller;

import com.edu.nju.alley.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @GetMapping("/search")
    public ResponseVO search(@RequestParam("keywords") String keywords){
        return ResponseVO.success().add(Arrays.asList("a","b"));
    }

}
