package com.foodie.foodie.domain.post.domain.repository;

import com.foodie.foodie.domain.post.domain.Post;
import com.foodie.foodie.domain.post.domain.PostAttachmentFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostAttachmentFileRepository extends CrudRepository<PostAttachmentFile, Long> {

    Optional<PostAttachmentFile> findAllByPost(Post post);

}
