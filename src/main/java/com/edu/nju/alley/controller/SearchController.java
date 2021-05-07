package com.edu.nju.alley.controller;

import com.edu.nju.alley.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @GetMapping("/")
    public ResponseVO search(@RequestParam("type") String type){
        return ResponseVO.success().msg(type);
    }

}
