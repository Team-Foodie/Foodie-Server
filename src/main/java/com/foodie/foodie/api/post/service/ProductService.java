package com.foodie.foodie.api.post.service;

import com.foodie.foodie.api.post.model.ProductItem;
import com.foodie.foodie.domain.product.domain.Product;
import com.foodie.foodie.domain.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductItem> getProductList(Pageable pageable) {
        return productRepository.findAll(pageable).stream().map(Product::toItem).collect(Collectors.toList());
    }
}
