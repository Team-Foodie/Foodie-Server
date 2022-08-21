package com.foodie.foodie.domain.post.domain.repository;

import com.foodie.foodie.domain.post.domain.PostAttachmentFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostAttachmentFileRepository extends CrudRepository<PostAttachmentFile, UUID> {

}
