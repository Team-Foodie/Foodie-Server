package com.foodie.foodie.domain.account.domain.jpo;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "account")
public class AccountJpo extends BaseEntity {

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

    public AccountJpo(Account account) {
        BeanUtils.copyProperties(account, this);
    }

    public Account toDomain() {
        Account account = new Account();
        BeanUtils.copyProperties(this, account);
        return account;
    }
}
