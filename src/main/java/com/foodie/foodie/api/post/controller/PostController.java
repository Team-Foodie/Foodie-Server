package com.foodie.foodie.api.post.controller;

import com.foodie.foodie.api.post.model.*;
import com.foodie.foodie.api.post.service.PostContentService;
import com.foodie.foodie.api.post.service.PostService;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import com.foodie.foodie.exception.InvalidAccountException;
import com.foodie.foodie.exception.InvalidCategoryTypeException;
import com.foodie.foodie.exception.InvalidPostException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    private final PostContentService postContentService;

    /**
     * 홈 화면. 카테고리별 5개씩 응답.
     * @return
     */
    @GetMapping("")
    public ResponseEntity<RestResponseData<PostResponse>> getAllcategoriesPost(Long accountIdx,
            @PageableDefault(size = 5, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("POST:INFO:RQST::: 홈 화면 정보 요청.");

        try {
            List<PostItem> postAllList = postService.getAllcategoriesPost(pageable);

            PostResponse postResponse = new PostResponse();
            postResponse.from(postAllList);
            log.info("POST:INFO:RESP::: 게시글 정보 응답 성공.");
            return new RestResponseData<>(ResultCode.SUCCESS, postResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException e) {
            log.info("POST:INFO:FAIL::: 게시글 정보 응답 실패.");
            return new RestResponseData<PostResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 카테고리 클릭 시 해당 카테고리에 대한 게시글을 보여주는 API. 페이지별 12개씩.
     * @param categoryType
     * @param postRequest
     * @return
     */
    @GetMapping("{categoryType}")
    public ResponseEntity<RestResponseData<PostResponse>> getCategoryList(
            @PathVariable String categoryType, PostRequest postRequest,
            @PageableDefault(size = 12, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("POST:INFO:RQST::: 게시글 정보 요청. categoryType = ({}), postRequest = ({})", categoryType, postRequest);

        try {

            PostCondition postCondition = postRequest.toCondition();
            List<PostItem> postAllList = postService.getPostList(categoryType, postCondition, pageable);

            PostResponse postResponse = new PostResponse();
            postResponse.from(postAllList);
            log.info("POST:INFO:RESP::: 게시글 정보 응답 성공.");
            return new RestResponseData<>(ResultCode.SUCCESS, postResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidCategoryTypeException e) {
            log.info("POST:INFO:FAIL::: 게시글 정보 응답 실패. e.getMessage = ({}), e = ({})", e.getMessage(), e);
            return new RestResponseData<PostResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{categoryType}")
    public ResponseEntity<RestResponseData<PostResponse>> writePost(@PathVariable String categoryType,
                                                                    @RequestBody PostWriteRequest postWriteRequest) {
        log.info("POST:WRIT:RQST::: 게시글 작성 요청. categoryType = ({}), postWriteRequest = ({})",
                categoryType, postWriteRequest);

        try {
            PostResponse postResponse = new PostResponse();
            PostItem postItem = postWriteRequest.toItem();
            List<PostItem> postItemList = new ArrayList<>();
            postItemList.add(postService.savePost(postItem));
            postResponse.from(postItemList);

            log.info("POST:WRIT:RESP::: 게시글 작성 응답 성공. categoryType = ({}), postWriteRequest = ({})",
                    categoryType, postWriteRequest);
            return new RestResponseData<>(ResultCode.SUCCESS, postResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException | InvalidPostException e) {
            log.warn("POST:WRIT:RESP::: 올바르지 않은 정보. e = ({})", e);
            return new RestResponseData<PostResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
