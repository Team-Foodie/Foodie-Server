package com.foodie.foodie.domain.follow.domain.jpo;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.follow.domain.Follow;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "follow")
public class FollowJpo extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account_idx", nullable = false)
    private Account targetAccount;

    public Follow toDomain() {
        Follow follow = new Follow();
        follow.setAccountIdx(account.getIdx());
        follow.setTargetAccountIdx(targetAccount.getIdx());
        return follow;
    }
}
