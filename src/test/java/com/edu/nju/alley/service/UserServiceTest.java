package com.edu.nju.alley.service;

import com.edu.nju.alley.vo.UserActionVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void allLikeMeTest() {
        List<UserActionVO> list = userService.allLikeMe(1);
    }

}
