package com.foodie.foodie.domain.follow.domain.repository;

import com.foodie.foodie.domain.follow.domain.Follow;
import com.foodie.foodie.domain.follow.domain.jpo.FollowJpo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends CrudRepository<FollowJpo, Long> {

    @Query(nativeQuery = true, value = "select idx, account_idx, target_account_idx from follow "
            + "where account_idx = :#{#follow.getAccountIdx()} and target_account_idx = :#{#follow.getTargetAccountIdx()}")
    FollowJpo findBySourceAndTargetId(@Param("follow") Follow follow);

    @Modifying
    @Query(nativeQuery = true, value = "insert into follow(account_idx, target_account_idx) values (:#{#follow.getAccountIdx()}, :#{#follow.getTargetAccountIdx()})")
    void insertFollow(Follow follow);

    @Modifying
    @Query(nativeQuery = true, value = "delete from follow where account_idx = :#{#follow.getAccountIdx()} "
            + "and target_account_idx = :#{#follow.getTargetAccountIdx()}")
    void deleteBySourceAndTargetId(@Param("follow") Follow follow);

    @Modifying
    @Query(nativeQuery = true, value = "select target_account_idx from follow where account_idx = :idx}")
    List<Long> findBySourceId(Long idx);
}
