package com.foodie.foodie.domain.post.domain.repository;

import com.foodie.foodie.domain.post.domain.PostContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostContentRepository extends CrudRepository<PostContent, Long> {

}
