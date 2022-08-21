package com.foodie.foodie.domain.scrap.domain;

import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.user.domain.User;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "scrap")
public class Scrap extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Scrap(String type, User user, Post post) {
        this.type = type;
        this.user = user;
        this.post = post;
    }
}
