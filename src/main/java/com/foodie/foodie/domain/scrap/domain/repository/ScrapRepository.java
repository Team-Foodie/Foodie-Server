package com.foodie.foodie.domain.scrap.domain.repository;

import com.foodie.foodie.domain.scrap.domain.jpo.ScrapJpo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends CrudRepository<ScrapJpo, Long> {

}
