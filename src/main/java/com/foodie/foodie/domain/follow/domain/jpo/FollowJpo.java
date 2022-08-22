package com.foodie.foodie.domain.follow.domain.jpo;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import com.foodie.foodie.domain.follow.domain.Follow;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "follow")
public class FollowJpo extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx", nullable = false)
    private AccountJpo accountJpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account_idx", nullable = false)
    private AccountJpo targetAccountJpo;

    public FollowJpo(Follow follow) {
        BeanUtils.copyProperties(follow, this);
    }

    public Follow toDomain() {
        Follow follow = new Follow();
        follow.setAccountIdx(accountJpo.getIdx());
        follow.setTargetAccountIdx(targetAccountJpo.getIdx());
        return follow;
    }
}
