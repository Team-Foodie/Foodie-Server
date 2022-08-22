package com.foodie.foodie.domain.follow.domain;
import lombok.*;

@Getter
@Setter
public class Follow {
    private Long accountIdx;
    private Long targetAccountIdx;
}
