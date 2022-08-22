package com.foodie.foodie.domain.account.domain;

import lombok.*;

@Getter
@Setter
public class Account {
    private Long idx;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String introduction;
}
