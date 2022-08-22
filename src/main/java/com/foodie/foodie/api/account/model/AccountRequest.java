package com.foodie.foodie.api.account.model;

import com.foodie.foodie.domain.account.domain.Account;
import lombok.Setter;

@Setter
public class AccountRequest {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String introduction;

    public Account toDomain() {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setName(name);
        account.setNickname(nickname);
        account.setIntroduction(introduction);
        return account;
    }
}
