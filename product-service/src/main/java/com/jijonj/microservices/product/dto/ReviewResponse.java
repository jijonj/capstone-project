package com.jijonj.microservices.product.dto;

public record ReviewResponse(String id, String productId, String userId, int rating, String comment, boolean flagged) { }