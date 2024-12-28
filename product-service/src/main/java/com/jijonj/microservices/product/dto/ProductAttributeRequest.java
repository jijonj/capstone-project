package com.jijonj.microservices.product.dto;

public record ProductAttributeRequest(String productId, String key, String value) { }