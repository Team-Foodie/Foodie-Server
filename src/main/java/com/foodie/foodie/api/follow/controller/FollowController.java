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

    /**
     * 유저가 팔로잉 한 사람들의 게시글들을 응답해주는 API. 페이지별로 8개씩, 최근 게시물 순
     * @param accountIdx
     * @return
     */
    @GetMapping("{accountIdx}")
    public ResponseEntity<RestResponseData<FollowResponse>> getFollowInfo(@PathVariable Long accountIdx) {
        log.info("FOLW:INFO:RQST::: 팔로우 정보 요청. accountIdx = ({})", accountIdx);

        try {
            List<Account> followInfoByAccount = followService.getFollowInfoByAccount(accountIdx);

            FollowResponse followResponse = new FollowResponse();
            followResponse.from(followInfoByAccount);
            log.info("FOLW:INFO:RESP::: 팔로우 정보 응답 성공. accountIdx = ({})", accountIdx);
            return new RestResponseData<>(ResultCode.SUCCESS, followResponse).buildResponseEntity(HttpStatus.OK);
        } catch (InvalidAccountException e) {
            log.info("FOLW:INFO:FAIL::: 존재하지 않는 계정 정보. accountIdx = ({})", accountIdx);
            return new RestResponseData<FollowResponse>(ResultCode.INVALID_STATE)
                    .buildResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{accountIdx}")
    public ResponseEntity<RestResponseData<FollowResponse>> insertAccount(@PathVariable Long accountIdx,
                                                        @RequestBody FollowToggleRequest followToggleRequest) {
        log.info("FOLW:TOGG:RQST::: 팔로우 추가/삭제 요청. accountIdx = ({}), request = ({})", accountIdx, followToggleRequest);
        Follow follow = followToggleRequest.toDomain();
        boolean isAdded = followService.toggleFollow(follow);

        FollowResponse followResponse = new FollowResponse();
        followResponse.setIsAdded(String.valueOf(isAdded));
        return new RestResponseData<>(ResultCode.SUCCESS, followResponse)
                .buildResponseEntity(HttpStatus.OK);
    }
}