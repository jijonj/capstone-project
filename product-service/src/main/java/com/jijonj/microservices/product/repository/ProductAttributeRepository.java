package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.ProductAttribute;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductAttributeRepository extends MongoRepository<ProductAttribute, String> {
    List<ProductAttribute> findByProductId(String productId);
    List<ProductAttribute> findByKeyContainingIgnoreCaseOrValueContainingIgnoreCase(String key, String value);
}