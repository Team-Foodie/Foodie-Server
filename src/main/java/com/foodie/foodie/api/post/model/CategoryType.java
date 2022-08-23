package com.foodie.foodie.api.post.model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum CategoryType {
    RECIPE,
    TIP,
    POPULAR_RESTAURANT,
    UNDEFINED
    ;

    private String getPathName() {
        return this.name().replace("_", "-").toLowerCase();
    }

    public static CategoryType findByPathName(String path) {
        return Arrays.stream(CategoryType.values()).filter(categoryType -> categoryType.getPathName().equals(path))
                .findAny().orElse(UNDEFINED);
    }
}
