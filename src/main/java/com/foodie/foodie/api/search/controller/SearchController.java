package com.foodie.foodie.api.search.controller;

import com.foodie.foodie.api.post.model.PostItem;
import com.foodie.foodie.api.search.model.SearchResponse;
import com.foodie.foodie.api.search.service.SearchService;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import com.foodie.foodie.exception.InvalidAccountException;
import com.foodie.foodie.exception.InvalidCategoryTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("{keyword}")
    public ResponseEntity<RestResponseData<SearchResponse>> getScrapInfo(
            @PathVariable String keyword, String categoryType,
            @PageableDefault(size = 12, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("KWRD:PAGE:RQST::: 스크랩 정보 요청. keyword = ({}), categoryType = ({}) 카테고리가 null이면 모두",
                keyword, categoryType);

        try {
            List<PostItem> postItemList = searchService.getPostListByKeywordAndCategoryType(
                    keyword, categoryType, pageable);

            SearchResponse searchResponse = new SearchResponse();
            log.info("KWRD:PAGE:RESP::: 스크랩 정보 응답 성공. keyword = ({})", keyword);
            return new RestResponseData<>(ResultCode.SUCCESS, searchResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException | InvalidCategoryTypeException e) {
            log.info("SCRP:PAGE:FAIL::: 잘못된 값이 넘어옴. e = ({})", e);
            return new RestResponseData<SearchResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
