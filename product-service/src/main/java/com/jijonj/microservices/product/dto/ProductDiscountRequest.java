package com.jijonj.microservices.product.dto;

import java.time.LocalDate;

public record ProductDiscountRequest(String productId, String type, double discountValue, LocalDate startDate, LocalDate endDate) { }