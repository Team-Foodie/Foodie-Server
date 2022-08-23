package com.foodie.foodie.api.account.model;

import com.foodie.foodie.domain.account.domain.Account;
import lombok.Getter;

@Getter
public class AccountResponse {
    private Account account;

    public void from(Account account) {
        this.account = account;
    }
}
