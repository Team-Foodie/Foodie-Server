package com.foodie.foodie.api.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foodie.foodie.domain.post.domain.Post;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    private List<Post> postList;

    public void from(List<Post> postList) {
        this.postList = postList;
    }
}
