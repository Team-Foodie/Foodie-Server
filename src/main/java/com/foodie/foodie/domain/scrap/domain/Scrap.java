package com.foodie.foodie.domain.scrap.domain;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.post.domain.jpo.PostJpo;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class Scrap {

    private String type;
    private AccountJpo accountJpo;
    private PostJpo post;

}
