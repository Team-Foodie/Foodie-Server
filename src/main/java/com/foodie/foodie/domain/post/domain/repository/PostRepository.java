package com.foodie.foodie.domain.post.domain.repository;

import com.foodie.foodie.domain.post.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    Optional<Post> findByIdx(Long idx);

    List<Post> findByCategory(String type);

    List<Post> findByCategoryAndTheme(String type, String theme);

    List<Post> findByAccountIdxAndCategory(Long accountId, String categoryType, Pageable pageable);

    List<Post> findByAccountIdx(Long accountIdx, Pageable pageable);


//    @Query(nativeQuery = true, value = "select * from post "
//            + "where account_idx = :#{#follow.getAccountIdx()} and target_account_idx = :#{#follow.getTargetAccountIdx()}")
//    Post findBySourceAndTargetId(@Param("condition") PostCondition postCondition);
}
