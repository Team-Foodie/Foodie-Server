package com.foodie.foodie.api.post.model;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.post.domain.PostContent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostItem {
    private Long idx;
    private String title;
    private String category;
    private String theme;
    private String keywordList;
    private String localKeyword;
    private Integer likeCount;
    private Account account;
    private String content;
    private String imgUrlList;
    private List<PostContent> postContentList = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostItem(Post post) {
        this.idx = post.getIdx();
        this.title = post.getTitle();
        this.category = post.getCategory();
        this.theme = post.getTheme();
        this.keywordList = post.getKeywordList();
        this.localKeyword = post.getLocalKeyword();
        this.likeCount = post.getLikeCount();
        this.account = post.getAccount();
        this.content = post.getContent();
        this.imgUrlList = post.getImgUrlList();
        this.postContentList = post.getPostContentList();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }

    public Post toEntity() {
        return new Post(idx, title, category.toUpperCase(), theme, keywordList, localKeyword,
                likeCount, account, content, imgUrlList, postContentList, createdAt);
    }

    @Builder
    public PostItem(Long idx, String title, String category, String theme, String keywordList, String localKeyword,
                    Integer likeCount, Account account, String content, String imgUrlList, List<PostContent> postContentList,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idx = idx;
        this.title = title;
        this.category = category;
        this.theme = theme;
        this.keywordList = keywordList;
        this.localKeyword = localKeyword;
        this.likeCount = likeCount;
        this.account = account;
        this.content = content;
        this.imgUrlList = imgUrlList;
        this.postContentList = postContentList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
