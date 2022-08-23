package com.foodie.foodie.api.post.model;

import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.post.domain.PostContent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostContentItem {
    private Long idx;
    private String type;
    private String content;
    private Boolean isThumbnail;
    private Post post;

    public PostContent toEntity() {
        return new PostContent(idx, type, content, isThumbnail, post);
    }

    @Builder
    public PostContentItem(Long idx, String type, String content, Boolean isThumbnail, Post post) {
        this.idx = idx;
        this.type = type;
        this.content = content;
        this.isThumbnail = isThumbnail;
        this.post = post;
    }
}
