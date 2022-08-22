package com.foodie.foodie.domain.product.domain;

import com.foodie.foodie.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
public class Product {
    private String name;
    private Integer price;
    private String productIntroduction;
}
