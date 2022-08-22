package com.foodie.foodie.domain.account.domain;

import com.foodie.foodie.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(50)", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String nickname;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String introduction;

    @Builder
    public Account(String email, String password, String name, String nickname, String introduction) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
    }
}
