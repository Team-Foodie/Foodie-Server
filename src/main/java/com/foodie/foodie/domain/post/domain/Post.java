package com.foodie.foodie.domain.post.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    private Long idx;
    private String title;
    private String content;
    private String keywordList;
    private String category;
    private String theme;
    private String localKeyword;
}
