package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByProductId(String productId);
    List<Review> findByRating(int rating);
    List<Review> findByUserId(String userId);
    List<Review> findByCommentContainingIgnoreCase(String keyword);
}