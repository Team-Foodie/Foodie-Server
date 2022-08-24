package com.foodie.foodie.api.post.model;

import lombok.*;

@Getter
@Setter
public class ProductItem {
    private String name;
    private Integer price;
    private float discountPercent;
    private String description;
}
