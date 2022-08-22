package com.foodie.foodie.api.follow.controller;

import com.foodie.foodie.api.follow.model.FollowToggleRequest;
import com.foodie.foodie.api.follow.model.FollowResponse;
import com.foodie.foodie.api.follow.service.FollowService;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import com.foodie.foodie.domain.follow.domain.Follow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;

    @GetMapping("{accountId}")
    public ResponseEntity<RestResponseData<FollowResponse>> getFollowInfo(@PathVariable Long accountId) {
        log.info("FOLW:INFO:RQST::: 팔로우 페이지 요청. accountId = ({})", accountId);

        followService.getFollowInfoByAccount(accountId);

        FollowResponse followResponse = new FollowResponse();
        log.info("FOLW:INFO:RESP::: 팔로우 페이지 응답 성공. accountId = ({})", accountId);
        return new RestResponseData<>(ResultCode.SUCCESS, followResponse).buildResponseEntity(HttpStatus.OK);
    }

    @PostMapping("{accountId}")
    public ResponseEntity<RestResponseData<FollowResponse>> insertAccount(@PathVariable Long accountId,
                                                        @RequestBody FollowToggleRequest followToggleRequest) {
        log.info("FOLW:TOGG:RQST::: 팔로우 추가/삭제 요청. accountId = ({}), request = ({})", accountId, followToggleRequest);
        Follow follow = followToggleRequest.toDomain();
        followService.toggleFollow(follow);
        return new RestResponseData<FollowResponse>(ResultCode.SUCCESS).buildResponseEntity(HttpStatus.OK);
    }
}