package com.foodie.foodie.domain.post.domain.jpo;


import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class PostJpo extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(60)", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String keywordList;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String category;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String theme;

    @Column(columnDefinition = "VARCHAR(20)")
    private String localKeyword;

    @Column(columnDefinition = "VARCHAR(255)")
    private String imageUrl;

    @ColumnDefault("0")
    private Integer likeCount;

    @ColumnDefault("false")
    private Boolean isDiscount;

    @ColumnDefault("false")
    private Boolean isPopular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx", nullable = false)
    private AccountJpo accountJpo;

    public PostJpo(Post post) {
        BeanUtils.copyProperties(post, this);
    }

    public Post toDomain() {
        Post post = new Post();
        BeanUtils.copyProperties(this, post);
        return post;
    }
}
