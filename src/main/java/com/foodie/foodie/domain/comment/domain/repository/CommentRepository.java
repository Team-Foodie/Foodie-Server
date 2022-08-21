package com.foodie.foodie.domain.comment.domain.repository;

import com.foodie.foodie.domain.comment.domain.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<Comment, UUID> {

}
