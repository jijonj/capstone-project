package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.ProductTag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductTagRepository extends MongoRepository<ProductTag, String> {
    List<ProductTag> findByProductId(String productId);
    List<ProductTag> findByTagId(String tagId);
}