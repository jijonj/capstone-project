package com.jijonj.microservices.product.dto;

import java.time.LocalDate;

public record ProductDiscountResponse(String id, String productId, String type, double discountValue, LocalDate startDate, LocalDate endDate, boolean active) { }