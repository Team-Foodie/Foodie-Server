package com.foodie.foodie.api.scrap.controller;

import com.foodie.foodie.api.scrap.model.ScrapItem;
import com.foodie.foodie.api.scrap.model.ScrapRequest;
import com.foodie.foodie.api.scrap.model.ScrapResponse;
import com.foodie.foodie.api.scrap.service.ScrapService;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import com.foodie.foodie.domain.scrap.domain.Scrap;
import com.foodie.foodie.exception.InvalidAccountException;
import com.foodie.foodie.exception.InvalidCategoryTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scrap")
public class ScrapController {
    private final ScrapService scrapService;

    /**
     * 내 정보 -> 스크랩 페이지. 모두 or 카테고리 별. 전부 12개 씩
     * @param accountId
     * @return
     */
    @GetMapping("{accountId}")
    public ResponseEntity<RestResponseData<ScrapResponse>> getScrapInfo(
            @PathVariable Long accountId, String categoryType,
            @PageableDefault(size = 12, sort = "post_idx", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("SCRP:PAGE:RQST::: 스크랩 정보 요청. accountId = ({}), categoryType = ({}) 카테고리가 null이면 모두",
                accountId, categoryType);

        try {
            List<ScrapItem> scrapItemList = scrapService.getScrapInfoByAccountAndCategoryType(
                    accountId, categoryType, pageable);

            ScrapResponse scrapResponse = new ScrapResponse();
            scrapResponse.from(scrapItemList);
            log.info("SCRP:PAGE:RESP::: 스크랩 정보 응답 성공. accountId = ({})", accountId);
            return new RestResponseData<>(ResultCode.SUCCESS, scrapResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException | InvalidCategoryTypeException e) {
            log.info("SCRP:PAGE:FAIL::: 잘못된 값이 넘어옴. e = ({})", e);
            return new RestResponseData<ScrapResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("")
    public ResponseEntity<RestResponseData<ScrapResponse>> toggleScrap(
            @RequestBody ScrapRequest scrapRequest) {
        log.info("SCRP:TOGG:RQST::: 스크랩 추가/삭제 요청. scrapRequest = ({})", scrapRequest);

        try {
            ScrapItem scrapItem = scrapRequest.toItem();
            Scrap scrap = scrapService.toggleScrap(scrapItem);

            ScrapResponse scrapResponse = new ScrapResponse();
            scrapResponse.setIsAdded(Objects.isNull(scrap) ? Boolean.FALSE.toString() : Boolean.TRUE.toString());
            return new RestResponseData<>(ResultCode.SUCCESS, scrapResponse)
                    .buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException | InvalidCategoryTypeException e) {
            log.info("SCRP:TOGG:FAIL::: 잘못된 값이 넘어옴. e = ({})", e);
            return new RestResponseData<ScrapResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}