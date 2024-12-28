package com.jijonj.microservices.product.dto;

public record ReviewRequest(String productId, String userId, int rating, String comment) { }