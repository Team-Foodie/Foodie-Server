package com.foodie.foodie.api.post.service;


import com.foodie.foodie.api.post.model.CategoryType;
import com.foodie.foodie.api.post.model.PostCondition;
import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.post.domain.repository.PostRepository;
import com.foodie.foodie.exception.InvalidCategoryTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<Post> getAllcategoriesPost() {
        return null;
    }

    public List<Post> getPostList(String type, PostCondition condition) {
        CategoryType categoryType = CategoryType.findByPathName(type);
        if (categoryType.equals(CategoryType.UNDEFINED)) {
            throw new InvalidCategoryTypeException("invalid categoryType.");
        }

        // 테마 값이 존재할 때
        if (StringUtils.hasText(condition.getTheme())) {
            List<Post> postThemeList = postRepository.findByCategoryAndTheme(categoryType.name(), condition.getTheme());

            // 키워드 값이 존재할 때
            if (StringUtils.hasText(condition.getKeyword())) {
                return postThemeList.stream().filter(post ->
                        Arrays.stream(post.getKeywordList().split(","))
                                .anyMatch(keyword -> keyword.equals(condition.getKeyword()))
                ).toList();
            }
            return postThemeList;
        }
        return postRepository.findByCategory(categoryType.name());
    }
}
