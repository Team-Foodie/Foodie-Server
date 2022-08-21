package com.foodie.foodie.domain.product.domain;

import com.foodie.foodie.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String productName;

    @Column(columnDefinition = "INTEGER", nullable = false)
    private Integer productPrice;

    @Builder
    public Product(String productName, Integer productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
