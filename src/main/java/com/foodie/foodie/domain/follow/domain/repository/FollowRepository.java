package com.foodie.foodie.domain.follow.domain.repository;

import com.foodie.foodie.domain.follow.domain.Follow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends CrudRepository<Follow, FollowId> {

}
