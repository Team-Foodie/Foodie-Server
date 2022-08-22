package com.foodie.foodie.domain.scrap.domain;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Scrap {

    private String type;
    private Account account;
    private Post post;

}
