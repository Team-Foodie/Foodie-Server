package com.foodie.foodie.api.follow.model;

import com.foodie.foodie.domain.follow.domain.Follow;
import lombok.Getter;

@Getter
public class FollowToggleRequest {
    private Long accountIdx;
    private Long targetAccountIdx;

    public Follow toDomain() {
        Follow follow = new Follow();
        follow.setAccountIdx(accountIdx);
        follow.setTargetAccountIdx(targetAccountIdx);
        return follow;
    }
}
