package com.foodie.foodie.domain.product.domain.repository;

import com.foodie.foodie.domain.product.domain.jpo.ProductJpo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductJpo, Long> {

}
