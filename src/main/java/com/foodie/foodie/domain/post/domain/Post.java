package com.foodie.foodie.domain.post.domain;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Post {
    private String title;
    private String content;
    private String keywordList;
    private String category;
    private String theme;
    private String localKeyword;
    private String imageUrl;
    private Integer likeCount;
    private Boolean isDiscount;
    private Boolean isPopular;
    private AccountJpo accountJpo;
}
