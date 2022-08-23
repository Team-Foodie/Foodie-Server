package com.foodie.foodie.api.post.model;

import lombok.Setter;

import java.util.List;

@Setter
public class PostWriteCondition {
    private String title;
    private String keywordList;
    private String category;
    private String theme;
    private String localKeyword;
    private Integer likeCount;
    private Long accountIdx;
    private List<String> contentList;
}
