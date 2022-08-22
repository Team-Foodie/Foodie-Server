package com.foodie.foodie.api.account.controller;

import com.foodie.foodie.api.account.model.AccountRequest;
import com.foodie.foodie.api.account.model.AccountResponse;
import com.foodie.foodie.api.account.service.AccountService;
import com.foodie.foodie.common.model.RestResponseData;
import com.foodie.foodie.common.model.ResultCode;
import com.foodie.foodie.domain.account.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("")
    public ResponseEntity<RestResponseData<AccountResponse>> insertAccount(@RequestBody AccountRequest accountRequest) {

        Account account = accountRequest.toDomain();
        accountService.insertAccount(account);
        return new RestResponseData<AccountResponse>(ResultCode.SUCCESS).buildResponseEntity(HttpStatus.OK);
    }
}
