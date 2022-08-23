package com.foodie.foodie.api.post.model;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class PostRequest {
    private String theme;
    private String keyword;
    private String pageNum;
    private Long accountIdx;

    public PostCondition toCondition() {
        PostCondition postCondition = new PostCondition();
        postCondition.setTheme(theme);
        postCondition.setKeyword(keyword);
        postCondition.setPageNum(pageNum);
        postCondition.setAccountIdx(accountIdx);
        return postCondition;
    }
}
