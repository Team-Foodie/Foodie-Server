package com.foodie.foodie.api.follow.model;

import com.foodie.foodie.domain.follow.domain.Follow;
import lombok.Getter;

@Getter
public class FollowToggleRequest {
    private Long accountId;
    private Long targetAccountId;

    public Follow toDomain() {
        Follow follow = new Follow();
        follow.setAccountIdx(accountId);
        follow.setTargetAccountIdx(targetAccountId);
        return follow;
    }
}
