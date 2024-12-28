package com.jijonj.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, String brand, String category, String description,
                             String skuCode, BigDecimal price) { }