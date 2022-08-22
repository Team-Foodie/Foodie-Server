package com.foodie.foodie.domain.comment.domain;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import com.foodie.foodie.domain.post.domain.Post;

import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String content;

    private LocalDateTime updatedAt;

    @NotNull
    @ColumnDefault("0")
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx", nullable = false)
    private AccountJpo accountJpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @Builder
    public Comment(String content, LocalDateTime updatedAt, Integer likeCount, AccountJpo accountJpo, Post post, Comment parentComment) {
        this.content = content;
        this.updatedAt = updatedAt;
        this.likeCount = likeCount;
        this.accountJpo = accountJpo;
        this.post = post;
        this.parentComment = parentComment;
    }
}
