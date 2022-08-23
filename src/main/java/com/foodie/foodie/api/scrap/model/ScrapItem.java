package com.foodie.foodie.api.scrap.model;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScrapItem {
    private Long idx;
    private String type;
    private Account account;
    private Post post;
}
