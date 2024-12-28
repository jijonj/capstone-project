package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.ProductDiscount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductDiscountRepository extends MongoRepository<ProductDiscount, String> {
    List<ProductDiscount> findByProductId(String productId);
    List<ProductDiscount> findByTypeContainingIgnoreCase(String type);
    List<ProductDiscount> findByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate);
}