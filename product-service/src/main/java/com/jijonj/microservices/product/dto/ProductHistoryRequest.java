package com.jijonj.microservices.product.dto;

import java.time.LocalDateTime;

public record ProductHistoryRequest(String productId, String actionType, String description, LocalDateTime actionDate) { }