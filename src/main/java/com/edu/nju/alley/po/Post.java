package com.edu.nju.alley.po;

import cn.hutool.core.date.DateUtil;
import com.edu.nju.alley.dto.PostDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Post {

    private Integer id;

    private Integer authId;

    private Integer userId;

    private Integer labelId;

    private String title;

    private String content;

    private Integer likeNum;

    private Integer commentNum;

    private Date createT;

    private Date lastModifiedT;

    private Integer anchorId;

    private Integer longitude;

    private Integer latitude;

    private String pictureUrl;

    //新建一个Post
    public Post(PostDTO postDTO) {
        this.userId = postDTO.getUserId();
        this.labelId = postDTO.getLabelId();
        this.title = postDTO.getTitle();
        this.content = postDTO.getContent();
        this.likeNum = 0;
        this.commentNum = 0;
        this.createT = DateUtil.date();
        this.lastModifiedT = DateUtil.date();
        this.anchorId = postDTO.getAnchorId();
        this.longitude = postDTO.getLongitude();
        this.latitude = postDTO.getLatitude();
        this.pictureUrl = postDTO.getPictureUrl();
    }

    public void updateByDTO(PostDTO postDTO) {
        this.title = postDTO.getTitle();
        this.content = postDTO.getContent();
        this.anchorId = postDTO.getAnchorId();
        this.lastModifiedT = DateUtil.date();
        this.longitude = postDTO.getLongitude();
        this.latitude = postDTO.getLatitude();
        this.pictureUrl = postDTO.getPictureUrl();
    }

}