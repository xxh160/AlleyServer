package com.edu.nju.alley;

import com.edu.nju.alley.controller.CommentController;
import com.edu.nju.alley.controller.PostController;
import com.edu.nju.alley.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UnitTest {

    private final UserController userController;

    private final CommentController commentController;

    private final PostController postController;

    @Autowired
    public UnitTest(UserController userController,
                    CommentController commentController,
                    PostController postController) {
        this.userController = userController;
        this.commentController = commentController;
        this.postController = postController;
    }

//    public UserDTO

    @Test

    public void TestOne() {
        

    }
}