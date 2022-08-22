package com.foodie.foodie.domain.post.domain;

import com.foodie.foodie.domain.post.domain.jpo.PostJpo;
import lombok.*;

@Getter
@Setter
public class PostContent {
    private String url;
    private PostJpo post;
    private Boolean isThumbnail;
}
