package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.ReviewRequest;
import com.jijonj.microservices.product.dto.ReviewResponse;
import com.jijonj.microservices.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.createReview(reviewRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getAllReviews(@RequestParam int page, @RequestParam int size) {
        return reviewService.getAllReviews(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> searchReviews(@RequestParam String query) {
        return reviewService.searchReviews(query);
    }

    @GetMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ReviewResponse> getReviewById(@PathVariable String reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ReviewResponse> updateReview(@PathVariable String reviewId, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.updateReview(reviewId, reviewRequest);
    }

    @PatchMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ReviewResponse> partialUpdateReview(@PathVariable String reviewId, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.partialUpdateReview(reviewId, reviewRequest);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/products/{productId}/reviews")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewsByProductId(@PathVariable String productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ReviewResponse> bulkCreateOrUpdateReviews(@RequestBody List<ReviewRequest> reviewRequests) {
        return reviewService.bulkCreateOrUpdateReviews(reviewRequests);
    }

    @PutMapping("/{reviewId}/flag")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ReviewResponse> flagReview(@PathVariable String reviewId) {
        return reviewService.flagReview(reviewId);
    }
}