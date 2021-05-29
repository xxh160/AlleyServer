package com.edu.nju.alley.service.impl;

import com.edu.nju.alley.enums.LabelSelectType;
import com.edu.nju.alley.service.AnchorService;
import com.edu.nju.alley.service.PostService;
import com.edu.nju.alley.util.Const;
import com.edu.nju.alley.vo.PostViewVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnchorServiceImpl implements AnchorService {

    private final PostService postService;

    @Autowired
    public AnchorServiceImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public List<PostViewVO> getAllPosts(Integer anchorId,
                                        Integer pageId,
                                        Integer sort,
                                        Integer label) {

        PageHelper.startPage(pageId, Const.pageSize);

        return postService.getAllSortedPosts(sort)
                .stream()
                .filter(c -> (c.getLabelId().equals(label) || label == LabelSelectType.ALL.getCode()))
                .filter(c -> c.getAnchorId().equals(anchorId))
                .map(t -> new PostViewVO(t.getId(), t.getLatitude(), t.getLongitude()))
                .collect(Collectors.toList());
    }

}
