package com.foodie.foodie.api.post.service;

import com.foodie.foodie.api.post.model.PostContentItem;
import com.foodie.foodie.domain.post.domain.PostContent;
import com.foodie.foodie.domain.post.domain.repository.PostContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostContentService {
    private final PostContentRepository postContentRepository;

    @Transactional
    public Long savePostContent(PostContentItem postContentItem) {
        return postContentRepository.save(postContentItem.toEntity()).getIdx();
    }

//    @Transactional
//    public List<Long> savePostContentAll(List<PostContentItem> postContentItemList) {
//        return postContentRepository.saveAll(postContentItemList);
//    }

    @Transactional
    public PostContentItem getPostContentItem(Long idx) {
        PostContent postContent = postContentRepository.findById(idx).orElse(PostContent.builder().build());

        return new PostContentItem(
                postContent.getIdx(), postContent.getType(), postContent.getContent(),
                postContent.getIsThumbnail(), postContent.getPost());
    }
}
