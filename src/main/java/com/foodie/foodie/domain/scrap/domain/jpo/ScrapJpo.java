package com.foodie.foodie.domain.scrap.domain.jpo;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import com.foodie.foodie.domain.post.domain.jpo.PostJpo;
import com.foodie.foodie.domain.product.domain.jpo.ProductJpo;
import com.foodie.foodie.domain.scrap.domain.Scrap;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "scrap")
public class ScrapJpo extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountJpo accountJpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostJpo post;

    public ScrapJpo(Scrap scrap) {
        BeanUtils.copyProperties(scrap, this);
    }

    public Scrap toDomain() {
        Scrap scrap = new Scrap();
        BeanUtils.copyProperties(this, scrap);
        return scrap;
    }

}