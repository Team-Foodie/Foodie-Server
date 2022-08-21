package com.foodie.foodie.domain.scrap.domain.repository;

import com.foodie.foodie.domain.scrap.domain.Scrap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScrapRepository extends CrudRepository<Scrap, UUID> {

}
