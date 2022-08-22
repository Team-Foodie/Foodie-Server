package com.foodie.foodie.api.post.model;

import lombok.Setter;

import java.util.List;

@Setter
public class PostWriteRequest {
    private String title;
    private List<String> keywordList;
    private String categoryType;
    private String theme;
    private String localKeyword;
    private Integer likeCount;
    private Long accountIdx;
    private List<String> contentList;

    public PostWriteCondition toCondition() {
        PostWriteCondition postWriteCondition = new PostWriteCondition();
        postWriteCondition.setTitle(title);
        postWriteCondition.setKeywordList(String.join(",", keywordList));
        postWriteCondition.setCategoryType(categoryType);
        postWriteCondition.setTheme(theme);
        postWriteCondition.setLocalKeyword(localKeyword);
        postWriteCondition.setLikeCount(likeCount);
        postWriteCondition.setAccountIdx(accountIdx);
        postWriteCondition.setContentList(contentList);
        return postWriteCondition;
    }
}
