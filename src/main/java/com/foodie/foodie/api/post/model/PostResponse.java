package com.foodie.foodie.api.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    private List<PostItem> postList;

    public void from(List<PostItem> postList) {
        this.postList = postList;
    }
}
