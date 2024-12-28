package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.ProductHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductHistoryRepository extends MongoRepository<ProductHistory, String> {
    List<ProductHistory> findByProductId(String productId);
    List<ProductHistory> findByActionTypeContainingIgnoreCase(String actionType);
    List<ProductHistory> findByActionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}