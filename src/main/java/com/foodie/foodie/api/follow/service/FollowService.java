package com.foodie.foodie.api.follow.service;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.account.domain.repository.AccountRepository;
import com.foodie.foodie.domain.follow.domain.Follow;
import com.foodie.foodie.domain.follow.domain.jpo.FollowJpo;
import com.foodie.foodie.domain.follow.domain.repository.FollowRepository;
import com.foodie.foodie.exception.InvalidAccountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {
    private final AccountRepository accountRepository;
    private final FollowRepository followRepository;
    public void getFollowInfoByAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).get().toDomain();
        if (account == null) {
            throw new InvalidAccountException("account doesn't exist.");
        }
        List<Long> targetIdList = followRepository.findBySourceId(account.getIdx());
        accountRepository.findAllById(targetIdList);
    }


    @Transactional
    public void toggleFollow(Follow follow) {
        FollowJpo followJpo = followRepository.findBySourceAndTargetId(follow);
        if (followJpo == null) {
            followRepository.insertFollow(follow);
            log.info("FOLW:TOGG:SUCS::: 팔로우 추가 성공. follow = ({})", follow);
        } else {
            followRepository.deleteBySourceAndTargetId(follow);
            log.info("FOLW:TOGG:SUCS::: 팔로우 삭제 성공. follow = ({})", follow);
        }
    }
}
