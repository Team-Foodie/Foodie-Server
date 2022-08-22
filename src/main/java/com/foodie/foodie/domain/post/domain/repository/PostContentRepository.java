package com.foodie.foodie.domain.post.domain.repository;

import com.foodie.foodie.domain.post.domain.jpo.PostContentJpo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostContentRepository extends CrudRepository<PostContentJpo, Long> {

}
