package com.foodie.foodie.domain.account.domain.repository;

import com.foodie.foodie.domain.account.domain.jpo.AccountJpo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountJpo, Long> {

}
