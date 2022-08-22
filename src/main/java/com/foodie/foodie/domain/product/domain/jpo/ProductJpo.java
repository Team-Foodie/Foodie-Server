package com.foodie.foodie.domain.product.domain.jpo;

import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.product.domain.Product;
import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class ProductJpo extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String productIntroduction;

    public ProductJpo(Product product) {
        BeanUtils.copyProperties(product, this);
    }

    public Product toDomain() {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        return product;
    }
}
