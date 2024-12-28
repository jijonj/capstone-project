package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.ReviewRequest;
import com.jijonj.microservices.product.dto.ReviewResponse;
import com.jijonj.microservices.product.model.Review;
import com.jijonj.microservices.product.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewRequest reviewRequest) {
        Review review = Review.builder()
                .productId(reviewRequest.productId())
                .userId(reviewRequest.userId())
                .rating(reviewRequest.rating())
                .comment(reviewRequest.comment())
                .flagged(false)
                .build();
        reviewRepository.save(review);
        log.info("Review created successfully");
        return mapToReviewResponse(review);
    }

    public List<ReviewResponse> getAllReviews(int page, int size) {
        Page<Review> reviews = reviewRepository.findAll(PageRequest.of(page, size));
        return reviews.stream().map(this::mapToReviewResponse).toList();
    }

    public List<ReviewResponse> searchReviews(String query) {
        List<Review> reviews = reviewRepository.findByCommentContainingIgnoreCase(query);
        return reviews.stream().map(this::mapToReviewResponse).toList();
    }

    public Optional<ReviewResponse> getReviewById(String reviewId) {
        return reviewRepository.findById(reviewId).map(this::mapToReviewResponse);
    }

    public Optional<ReviewResponse> updateReview(String reviewId, ReviewRequest reviewRequest) {
        return reviewRepository.findById(reviewId).map(review -> {
            review.setProductId(reviewRequest.productId());
            review.setUserId(reviewRequest.userId());
            review.setRating(reviewRequest.rating());
            review.setComment(reviewRequest.comment());
            reviewRepository.save(review);
            return mapToReviewResponse(review);
        });
    }

    public Optional<ReviewResponse> partialUpdateReview(String reviewId, ReviewRequest reviewRequest) {
        return reviewRepository.findById(reviewId).map(review -> {
            if (reviewRequest.productId() != null) review.setProductId(reviewRequest.productId());
            if (reviewRequest.userId() != null) review.setUserId(reviewRequest.userId());
            if (reviewRequest.rating() != 0) review.setRating(reviewRequest.rating());
            if (reviewRequest.comment() != null) review.setComment(reviewRequest.comment());
            reviewRepository.save(review);
            return mapToReviewResponse(review);
        });
    }

    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<ReviewResponse> getReviewsByProductId(String productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().map(this::mapToReviewResponse).toList();
    }

    public List<ReviewResponse> bulkCreateOrUpdateReviews(List<ReviewRequest> reviewRequests) {
        List<Review> reviews = reviewRequests.stream().map(request -> Review.builder()
                .productId(request.productId())
                .userId(request.userId())
                .rating(request.rating())
                .comment(request.comment())
                .flagged(false)
                .build()).toList();
        reviewRepository.saveAll(reviews);
        return reviews.stream().map(this::mapToReviewResponse).toList();
    }

    public Optional<ReviewResponse> flagReview(String reviewId) {
        return reviewRepository.findById(reviewId).map(review -> {
            review.setFlagged(true);
            reviewRepository.save(review);
            return mapToReviewResponse(review);
        });
    }

    private ReviewResponse mapToReviewResponse(Review review) {
        return new ReviewResponse(review.getId(), review.getProductId(), review.getUserId(), review.getRating(), review.getComment(), review.isFlagged());
    }
}