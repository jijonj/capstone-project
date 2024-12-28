package com.jijonj.microservices.product.dto;

import java.time.LocalDateTime;

public record ProductHistoryResponse(String id, String productId, String actionType, String description, LocalDateTime actionDate) { }