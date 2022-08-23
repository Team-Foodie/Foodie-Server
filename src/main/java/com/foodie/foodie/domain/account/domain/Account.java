package com.foodie.foodie.domain.account.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

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
    public Account(Long idx, String email, String password, String name, String nickname, String introduction) {
        this.idx = idx;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.introduction = introduction;
    }
}
