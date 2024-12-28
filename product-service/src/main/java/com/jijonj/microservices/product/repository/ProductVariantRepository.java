package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.ProductVariant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductVariantRepository extends MongoRepository<ProductVariant, String> {
    List<ProductVariant> findByProductId(String productId);
    List<ProductVariant> findBySkuContainingIgnoreCase(String sku);
}