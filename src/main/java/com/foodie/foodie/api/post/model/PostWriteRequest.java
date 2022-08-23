package com.foodie.foodie.api.post.model;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.post.domain.PostContent;
import lombok.Builder;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
public class PostWriteRequest {
    private Long idx;
    private String title;
    private List<String> keywordList;
    private String category;
    private String theme;
    private String localKeyword;
    private Integer likeCount = 0; // 기본 값 0
    private Long accountIdx;
    private List<String> contentList;

    public PostWriteCondition toCondition() {
        PostWriteCondition postWriteCondition = new PostWriteCondition();
        postWriteCondition.setTitle(title);
        postWriteCondition.setKeywordList(String.join(",", keywordList));
        postWriteCondition.setCategory(category);
        postWriteCondition.setTheme(theme);
        postWriteCondition.setLocalKeyword(localKeyword);
        postWriteCondition.setLikeCount(likeCount);
        postWriteCondition.setAccountIdx(accountIdx);
        postWriteCondition.setContentList(contentList);
        return postWriteCondition;
    }

    @Builder
    public PostItem toItem() {
        PostItem postItem = new PostItem();
        postItem.setIdx(idx);
        postItem.setTitle(title);
        postItem.setKeywordList(String.join(",", keywordList));
        postItem.setCategory(category);
        postItem.setTheme(theme);
        postItem.setLocalKeyword(localKeyword);
        postItem.setLikeCount(likeCount);
        postItem.setAccount(Account.builder().idx(accountIdx).build());
        postItem.setPostContentList(contentList.stream().map(content -> PostContent.builder()
                        .type(content.contains("http") ? "resource" : "text").content(content).build())
                .collect(Collectors.toList()));
        return postItem;
    }
}
