package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String brand, String category);
    List<Product> findByCategory(String category);

    List<Product> findByBrand(String brandId);
}