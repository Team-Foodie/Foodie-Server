package com.foodie.foodie.domain.product.domain;

import com.foodie.foodie.api.post.model.ProductItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "real", nullable = false)
    private float discountPercent;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    public Product(ProductItem productItem) {
        BeanUtils.copyProperties(productItem, this);
    }

    public ProductItem toItem() {
        ProductItem product = new ProductItem();
        BeanUtils.copyProperties(this, product);
        return product;
    }
}
