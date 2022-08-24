package com.foodie.foodie.api.follow.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foodie.foodie.api.post.model.PostItem;
import com.foodie.foodie.domain.account.domain.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FollowResponse {
    private List<Account> accountList;
    private List<PostItem> postItemList;
    @Setter
    private String isAdded;

    public void putPostItemListFrom(List<PostItem> postItemList) {
        this.postItemList = postItemList;
    }
    public void from(List<Account> accountList) {
        this.accountList = accountList;
    }
}
