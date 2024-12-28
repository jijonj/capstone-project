package com.jijonj.microservices.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String brand, String category, String description,
                              String skuCode, BigDecimal price, boolean active) { }