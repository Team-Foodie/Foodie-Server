package com.foodie.foodie.domain.comment.domain.jpo;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import com.foodie.foodie.domain.comment.domain.Comment;
import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.post.domain.jpo.PostJpo;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
public class CommentJpo extends BaseEntity {

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
    @JoinColumn(name = "post_idx", nullable = false)
    private PostJpo post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_idx", nullable = false)
    private CommentJpo parentComment;

    public CommentJpo(Comment comment) {
        BeanUtils.copyProperties(comment, this);
    }

    public Comment toDomain() {
        Comment comment = new Comment();
        BeanUtils.copyProperties(this, comment);
        return comment;
    }
}
