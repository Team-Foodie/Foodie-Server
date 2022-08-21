package com.foodie.foodie.domain.post.domain.repository;

import com.foodie.foodie.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {

}
