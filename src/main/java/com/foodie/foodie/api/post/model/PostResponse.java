package com.foodie.foodie.api.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    private List<ProductItem> productItemList;
    private List<PostItem> postList;

    public void putProductItemListFrom(List<ProductItem> productItemList) {
        this.productItemList = productItemList;
    }
    public void from(List<PostItem> postList) {
        this.postList = postList;
    }
}
