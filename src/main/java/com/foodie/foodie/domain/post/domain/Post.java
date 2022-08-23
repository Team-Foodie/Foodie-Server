package com.foodie.foodie.domain.post.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.foodie.foodie.domain.account.domain.Account;
import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

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

    @Setter
    @Column(columnDefinition = "TEXT")
    private String contentOrder;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PostContent> postContentList = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Post(Long idx, String title, String category, String theme, String keywordList, String localKeyword,
                Integer likeCount, Account account, String contentOrder, List<PostContent> postContentList, LocalDateTime createdAt) {
        this.idx = idx;
        this.title = title;
        this.category = category;
        this.theme = theme;
        this.keywordList = keywordList;
        this.localKeyword = localKeyword;
        this.likeCount = likeCount;
        this.account = account;
        this.contentOrder = contentOrder;
        this.postContentList = postContentList;
        this.createdAt = createdAt;
    }
}
