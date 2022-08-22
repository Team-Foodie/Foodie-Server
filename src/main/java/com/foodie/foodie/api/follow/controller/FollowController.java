package com.foodie.foodie.api.follow.controller;

import com.foodie.foodie.api.follow.model.FollowToggleRequest;
import com.foodie.foodie.api.follow.model.FollowResponse;
import com.foodie.foodie.api.follow.service.FollowService;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.follow.domain.Follow;
import com.foodie.foodie.exception.InvalidAccountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;

    @GetMapping("{accountId}")
    public ResponseEntity<RestResponseData<FollowResponse>> getFollowInfo(@PathVariable Long accountId) {
        log.info("FOLW:INFO:RQST::: 팔로우 정보 요청. accountId = ({})", accountId);

        try {
            List<Account> followInfoByAccount = followService.getFollowInfoByAccount(accountId);

            FollowResponse followResponse = new FollowResponse();
            followResponse.from(followInfoByAccount);
            log.info("FOLW:INFO:RESP::: 팔로우 정보 응답 성공. accountId = ({})", accountId);
            return new RestResponseData<>(ResultCode.SUCCESS, followResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException e) {
            log.info("FOLW:INFO:FAIL::: 존재하지 않는 계정 정보. accountId = ({})", accountId);
            return new RestResponseData<FollowResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{accountId}")
    public ResponseEntity<RestResponseData<FollowResponse>> insertAccount(@PathVariable Long accountId,
                                                        @RequestBody FollowToggleRequest followToggleRequest) {
        log.info("FOLW:TOGG:RQST::: 팔로우 추가/삭제 요청. accountId = ({}), request = ({})", accountId, followToggleRequest);
        Follow follow = followToggleRequest.toDomain();
        boolean isAdded = followService.toggleFollow(follow);

        FollowResponse followResponse = new FollowResponse();
        followResponse.setIsAdded(String.valueOf(isAdded));
        return new RestResponseData<>(ResultCode.SUCCESS, followResponse)
                .buildResponseEntity(HttpStatus.OK);
    }
}