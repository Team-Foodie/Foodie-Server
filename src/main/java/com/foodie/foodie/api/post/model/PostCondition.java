package com.foodie.foodie.api.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCondition {
    private String theme;
    private String keyword;
    private String pageNum;
    private Long accountIdx;
}
