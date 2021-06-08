package com.edu.nju.alley.controller;

import com.edu.nju.alley.dto.CommentDTO;
import com.edu.nju.alley.dto.PostDTO;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "post")
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation("返回所有帖子预览;sortId决定排序方式，labelId决定标签筛选方式")
    @GetMapping("/all")
    public ResponseVO getAllPosts(@RequestParam Integer sort,
                                  @RequestParam Integer label) {
        return ResponseVO.success().add(postService.getAllPostView(sort, label));
    }

    @ApiOperation("返回特定的帖子;通过帖子id返回特定的帖子")
    @GetMapping("/view/{postId}")
    public ResponseVO getSpecificPost(@PathVariable Integer postId) {
        return ResponseVO.success().add(postService.getSpecificPost(postId));
    }

    @ApiOperation("更新帖子;从request body中接收PostDTO来部分重写帖子以达到更新效果")
    @PostMapping("/update/{postId}")
    public ResponseVO updatePost(@PathVariable Integer postId,
                                 @RequestBody PostDTO postDTO) {
        postService.updatePost(postId, postDTO);
        return ResponseVO.success();
    }

    @ApiOperation("点赞或者取消点赞帖子;将对应帖子的点赞数加/减一，通过查询user_like_post数据库来判断是点赞还是取消点赞，并且向user_like_post数据库里加/减一条记录")
    @PostMapping("/like")
    public ResponseVO likePost(@RequestParam Integer postId,
                               @RequestParam Integer userId) {
        return ResponseVO.success().add(postService.likePost(postId, userId));
    }

    @ApiOperation("评论帖子;通过PostCommentDTO的评论信息新建立一个评论加入数据库，更新post_comment_rel、user_comment_rel数据库以及相关post信息（commentNum等）")
    @PostMapping("/comment")
    public ResponseVO commentPost(@RequestBody CommentDTO commentDTO) {
        return ResponseVO.success().add(postService.commentPost(commentDTO));
    }

    @ApiOperation("新建帖子;新建一个帖子，更新post数据库和user_post_rel数据库")
    @PostMapping("/create")
    public ResponseVO createPost(@RequestBody PostDTO postDTO) {
        return ResponseVO.success().add(postService.createPost(postDTO));
    }

    @ApiOperation("删除帖子;删除帖子，更新相应数据库，注意user id在post表中有存")
    @DeleteMapping("/delete/{postId}")
    public ResponseVO deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseVO.success();
    }

}
