package com.foodie.foodie.domain.product.domain.repository;

import com.foodie.foodie.domain.product.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll(Pageable pageable);
}
