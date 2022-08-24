package com.foodie.foodie.api.post.service;


import com.foodie.foodie.api.post.model.*;
import com.foodie.foodie.domain.account.domain.repository.AccountRepository;
import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.post.domain.PostContent;
import com.foodie.foodie.domain.post.domain.repository.PostContentRepository;
import com.foodie.foodie.domain.post.domain.repository.PostRepository;
import com.foodie.foodie.exception.InvalidAccountException;
import com.foodie.foodie.exception.InvalidCategoryTypeException;
import com.foodie.foodie.exception.InvalidPostException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostContentRepository postContentRepository;
    private final AccountRepository accountRepository;

    public List<PostItem> getAllcategoriesPost(Pageable pageable) {
        List<PostItem> postItemList = new ArrayList<>();
        Arrays.stream(CategoryType.values()).filter(categoryType -> !categoryType.equals(CategoryType.UNDEFINED))
                .forEach(categoryType ->
                    postItemList.addAll(postRepository.findByCategory(categoryType.name(), pageable).stream()
                            .map(Post::toItem).collect(Collectors.toList()))
                );
        return postItemList;
    }

    public List<PostItem> getPostList(String type, PostCondition condition, Pageable pageable) {
        CategoryType categoryType = CategoryType.findByPathName(type);
        if (categoryType.equals(CategoryType.UNDEFINED)) {
            throw new InvalidCategoryTypeException("invalid categoryType.");
        }

        // 테마 값만 존재할 때
        if (StringUtils.hasText(condition.getTheme())) {
            return postRepository.findByCategoryAndTheme(categoryType.name(), condition.getTheme(), pageable)
                    .stream().map(PostItem::new).collect(Collectors.toList());
        }

        // 테마 값, 키워드 값 둘다 없을 때
        return postRepository.findByCategory(categoryType.name(), pageable).stream().map(PostItem::new)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public List<PostItem> searchPostList() {
//
//        // 테마 값 & 키워드 값이 존재할 때
//        if (StringUtils.hasText(condition.getKeyword()) && StringUtils.hasText(condition.getTheme())) {
//            List<Post> postThemeList = postRepository.findByCategoryAndTheme(
//                    categoryType.name(), condition.getTheme(), pageable);
//
//            return postThemeList.stream().filter(post ->
//                    Arrays.stream(post.getKeywordList().split(","))
//                            .anyMatch(keyword -> keyword.equals(condition.getKeyword()))
//            ).collect(Collectors.toList()).stream().map(PostItem::new).collect(Collectors.toList());
//        }
//
//        // 키워드 값만 존재할 때
//        if (StringUtils.hasText(condition.getKeyword())) {
//            return postRepository.findByCategory(categoryType.name()).stream().filter(post -> Arrays.stream(post.getKeywordList().split(","))
//                            .anyMatch(keyword -> keyword.equals(condition.getKeyword()))).map(PostItem::new)
//                    .collect(Collectors.toList());
//        }
//    }

    @Transactional
    public PostItem savePost(PostItem postItem) {
        postItem.setCreatedAt(LocalDateTime.now());
        Post post = postItem.toEntity();
        Long postIdx = postRepository.save(post).getIdx();
        List<PostContentItem> postContentItemList = post.getPostContentList().stream().map(postContent ->
            PostContentItem.builder().content(postContent.getContent()).isThumbnail(false)
                    .post(Post.builder().idx(postIdx).build()).type(postContent.getContent().contains("http") ? "resource" : "text").build()
        ).collect(Collectors.toList());

        List<PostContent> postContentList = new ArrayList<>();
        postContentRepository.saveAll(postContentItemList.stream().map(PostContentItem::toEntity)
                .collect(Collectors.toList())).forEach(postContentList::add);

        String contentOrder = postContentList.stream()
                .map(postContent -> postContent.getIdx().toString()).collect(Collectors.joining(","));
        Post postInfo = postRepository.findById(postIdx).orElseThrow(() -> new InvalidPostException("post doesn't exist."));
        postInfo.setContentOrder(contentOrder);
        Post savedPost = postRepository.save(postInfo);

        PostItem postItemResult = new PostItem(savedPost);
        postItemResult.setPostContentList(postContentList);
        postItemResult.setAccount(accountRepository.findById(savedPost.getAccount().getIdx()).orElseThrow(() ->
                new InvalidAccountException("account doesn't exist.")));

        return postItemResult;
    }

    public List<PostItem> getPostInfoByAccountAndCategoryType(Long accountIdx, String categoryType, Pageable pageable) {
        // 모두 페이지일 경우.
        if (!StringUtils.hasText(categoryType)) {
            return postRepository.findByAccountIdx(accountIdx, pageable).stream().map(Post::toItem)
                    .collect(Collectors.toList());
        }

        CategoryType type = CategoryType.findByPathName(categoryType);
        if (type.equals(CategoryType.UNDEFINED)) {
            throw new InvalidCategoryTypeException("categoryType doesn't exist.");
        }

        return postRepository.findByAccountIdxAndCategory(accountIdx, type.name(), pageable).stream().map(Post::toItem)
                .collect(Collectors.toList());
    }
}
