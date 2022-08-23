package com.foodie.foodie.api.scrap.service;

import com.foodie.foodie.api.post.model.CategoryType;
import com.foodie.foodie.api.scrap.model.ScrapItem;
import com.foodie.foodie.domain.scrap.domain.Scrap;
import com.foodie.foodie.domain.scrap.domain.repository.ScrapRepository;
import com.foodie.foodie.exception.InvalidCategoryTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    public List<ScrapItem> getScrapInfoByAccountAndCategoryType(Long accountIdx, String categoryType,
                                                                Pageable pageable) {
        // 모두 페이지일 경우.
        if (!StringUtils.hasText(categoryType)) {
            return scrapRepository.findByAccountIdx(accountIdx, pageable).stream().map(Scrap::toItem)
                    .collect(Collectors.toList());
        }

        CategoryType type = CategoryType.findByPathName(categoryType);
        if (type.equals(CategoryType.UNDEFINED)) {
            throw new InvalidCategoryTypeException("categoryType doesn't exist.");
        }

        return scrapRepository.findByAccountIdxAndType(accountIdx, type.name(), pageable).stream().map(Scrap::toItem)
                .collect(Collectors.toList());
    }

    @Transactional
    public Scrap toggleScrap(ScrapItem scrapItem) {

        CategoryType type = CategoryType.findByPathName(scrapItem.getType());
        if (type.equals(CategoryType.UNDEFINED)) {
            throw new InvalidCategoryTypeException("categoryType doesn't exist.");
        }
        scrapItem.setType(type.name());

        Scrap scrap = scrapRepository.findByAccountIdxAndPostIdx(scrapItem.getAccount().getIdx(),
                scrapItem.getPost().getIdx());

        if (Objects.isNull(scrap)) {
            return scrapRepository.save(new Scrap(scrapItem));
        }
        scrapRepository.delete(scrap);
        return null;
    }
}
