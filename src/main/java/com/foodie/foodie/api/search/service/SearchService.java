package com.foodie.foodie.api.search.service;

import com.foodie.foodie.api.post.model.PostItem;
import com.foodie.foodie.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PostService postService;
    public List<PostItem> getPostListByKeywordAndCategoryType(String keyword, String categoryType, Pageable pageable) {
        return null;
    }
}
