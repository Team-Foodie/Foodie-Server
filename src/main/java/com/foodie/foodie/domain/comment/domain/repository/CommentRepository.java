package com.foodie.foodie.domain.comment.domain.repository;

import com.foodie.foodie.domain.comment.domain.jpo.CommentJpo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentJpo, Long> {

}
