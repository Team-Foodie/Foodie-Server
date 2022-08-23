package com.foodie.foodie.domain.scrap.domain;

import com.foodie.foodie.api.scrap.model.ScrapItem;
import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.post.domain.Post;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "scrap")
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_idx", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_idx", nullable = false)
    private Post post;

    @Builder
    public Scrap(Long idx, String type, Account account, Post post) {
        this.idx = idx;
        this.type = type;
        this.account = account;
        this.post = post;
    }

    public Scrap(ScrapItem scrapItem) {
        BeanUtils.copyProperties(scrapItem, this);
    }

    public ScrapItem toItem() {
        ScrapItem scrapItem = new ScrapItem();
        scrapItem.setIdx(idx);
        scrapItem.setType(type);
        scrapItem.setAccount(account);
        scrapItem.setPost(post);
        return scrapItem;
    }
}