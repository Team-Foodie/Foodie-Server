package com.foodie.foodie.api.account.model;

import com.foodie.foodie.domain.account.domain.Account;
import lombok.Setter;

@Setter
public class AccountRequest {
    private Long idx;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String introduction;

    public Account toDomain() {
        return new Account(idx, email, password, name, nickname, introduction);
    }
}
