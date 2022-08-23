package com.foodie.foodie.api.scrap.model;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.post.domain.Post;
import lombok.Getter;

@Getter
public class ScrapRequest {
    private Long accountIdx;
    private Long postIdx;
    private String categoryType;

    public ScrapItem toItem() {
        ScrapItem scrapItem = new ScrapItem();
        scrapItem.setAccount(Account.builder().idx(accountIdx).build());
        scrapItem.setPost(Post.builder().idx(postIdx).build());
        scrapItem.setType(categoryType);
        return scrapItem;
    }
}
