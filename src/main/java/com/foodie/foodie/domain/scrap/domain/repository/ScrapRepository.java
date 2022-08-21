package com.foodie.foodie.domain.scrap.domain.repository;

import com.foodie.foodie.domain.scrap.domain.Scrap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends CrudRepository<Scrap, Long> {

}
