package com.foodie.foodie.domain.account.domain.repository;

import com.foodie.foodie.domain.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
