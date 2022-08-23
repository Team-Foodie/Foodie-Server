package com.foodie.foodie.api.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foodie.foodie.api.post.model.PostItem;
import com.foodie.foodie.domain.account.domain.Account;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
    private Account account;

    private List<PostItem> postList;

    public void from(Account account) {
        this.account = account;
    }

    public void from(List<PostItem> postList) {
        this.postList = postList;
    }
}
