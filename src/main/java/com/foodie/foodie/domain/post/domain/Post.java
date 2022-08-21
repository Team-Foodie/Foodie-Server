package com.foodie.foodie.domain.post.domain;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String keyword;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String category;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String theme;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String localKeyword;

    @NotNull
    @ColumnDefault("0")
    private Integer likeCount;

    @NotNull
    @ColumnDefault("false")
    private Boolean isDiscount;

    @NotNull
    @ColumnDefault("false")
    private Boolean isPopular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Builder
    public Post(String title, String content, String keyword, String category, String theme,
                String localKeyword, Integer likeCount, Boolean isDiscount, Boolean isPopular, Account account) {
        this.title = title;
        this.content = content;
        this.keyword = keyword;
        this.category = category;
        this.theme = theme;
        this.localKeyword = localKeyword;
        this.likeCount = likeCount;
        this.isDiscount = isDiscount;
        this.isPopular = isPopular;
        this.account = account;
    }

}

