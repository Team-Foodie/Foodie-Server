package com.foodie.foodie.api.account.controller;

import com.foodie.foodie.api.account.model.AccountRequest;
import com.foodie.foodie.api.account.model.AccountResponse;
import com.foodie.foodie.api.account.service.AccountService;
import com.foodie.foodie.api.post.model.PostItem;
import com.foodie.foodie.api.post.service.PostService;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import com.foodie.foodie.domain.account.domain.Account;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    private final PostService postService;

    /**
     * 내 정보 -> 내가 쓴 글. 모두 or 카테고리 별. 전부 12개 씩
     *
     * 좋아요, 스크랩, 팔로우 수 추가 작업 해야 함.
     *
     * @param accountIdx
     * @return
     */
    @GetMapping("{accountIdx}/post")
    public ResponseEntity<RestResponseData<AccountResponse>> getPostListInfo(
            @PathVariable Long accountIdx, String categoryType,
            @PageableDefault(size = 12, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("ACCT:POST:RQST::: 내가 쓴 글 정보 요청. accountIdx = ({}), categoryType = ({}) 카테고리가 null이면 모두",
                accountIdx, categoryType);

        try {
            List<PostItem> postItemList = postService.getPostInfoByAccountAndCategoryType(
                    accountIdx, categoryType, pageable);

            Account account = accountService.getAccountByIdx(accountIdx);

            AccountResponse accountResponse = new AccountResponse();
            accountResponse.from(account);
            accountResponse.from(postItemList);
            log.info("ACCT:POST:RESP::: 내가 쓴 글 정보 응답 성공. accountIdx = ({})", accountIdx);
            return new RestResponseData<>(ResultCode.SUCCESS, accountResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException | InvalidCategoryTypeException e) {
            log.info("SCRP:PAGE:FAIL::: 잘못된 값이 넘어옴. e.getMessage = ({}), e = ({})", e.getMessage(), e);
            return new RestResponseData<AccountResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<RestResponseData<AccountResponse>> insertAccount(@RequestBody AccountRequest accountRequest) {

        Account account = accountRequest.toDomain();
        Account savedAccount = accountService.insertAccount(account);
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.from(savedAccount);
        return new RestResponseData<>(ResultCode.SUCCESS, accountResponse).buildResponseEntity(HttpStatus.OK);
    }
}
