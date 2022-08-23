package com.foodie.foodie.domain.scrap.domain.repository;

import com.foodie.foodie.domain.scrap.domain.Scrap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends CrudRepository<Scrap, Long> {
    List<Scrap> findByAccountIdx(Long accountIdx, Pageable pageable);

    List<Scrap> findByAccountIdxAndType(Long accountIdx, String type, Pageable pageable);

    Scrap findByAccountIdxAndPostIdx(Long accountIdx, Long postIdx);
}
