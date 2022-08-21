package com.foodie.foodie.domain.comment.domain;

import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.user.domain.User;
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

    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String content;

    private LocalDateTime updatedAt;

    @NotNull
    @ColumnDefault("0")
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @Builder
    public Comment(String content, LocalDateTime updatedAt, Integer likeCount, User user, Post post, Comment parentComment) {
        this.content = content;
        this.updatedAt = updatedAt;
        this.likeCount = likeCount;
        this.user = user;
        this.post = post;
        this.parentComment = parentComment;
    }
}
