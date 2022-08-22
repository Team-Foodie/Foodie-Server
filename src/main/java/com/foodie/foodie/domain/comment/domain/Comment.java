package com.foodie.foodie.domain.comment.domain;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;

import com.foodie.foodie.domain.comment.domain.jpo.CommentJpo;
import com.foodie.foodie.domain.post.domain.jpo.PostJpo;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class Comment extends BaseEntity {
    private String content;
    private LocalDateTime updatedAt;
    private Integer likeCount;
    private AccountJpo accountJpo;
    private PostJpo postJpo;
    private CommentJpo parentComment;
}
