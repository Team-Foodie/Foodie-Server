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
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String title;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String category;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String theme;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String keywordList;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String localKeyword;

    @NotNull
    @ColumnDefault("0")
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_idx", nullable = false)
    private Account account;

    @Column(columnDefinition = "TEXT")
    private String contentOrder;

    @OneToMany(mappedBy = "post")
    private List<PostContent> postContentList = new ArrayList<>();

    @Builder
    public Post(String title, String category, String theme, String keywordList, String localKeyword, Integer likeCount, Account account, String contentOrder, List<PostContent> postContentList) {
        this.title = title;
        this.category = category;
        this.theme = theme;
        this.keywordList = keywordList;
        this.localKeyword = localKeyword;
        this.likeCount = likeCount;
        this.account = account;
        this.contentOrder = contentOrder;
        this.postContentList = postContentList;
    }
}
