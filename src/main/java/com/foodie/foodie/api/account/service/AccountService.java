package com.foodie.foodie.api.account.service;

import com.foodie.foodie.domain.account.domain.Account;
import com.foodie.foodie.domain.account.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void insertAccount(Account account) {
        accountRepository.save(account);
    }
}
