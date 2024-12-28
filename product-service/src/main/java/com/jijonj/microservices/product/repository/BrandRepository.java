package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BrandRepository extends MongoRepository<Brand, String> {
    List<Brand> findByNameContainingIgnoreCase(String name);
}