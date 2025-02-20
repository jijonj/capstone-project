package com.jijonj.microservices.product.dto;

import java.math.BigDecimal;

public record ProductVariantResponse(String id, String productId, String sku, String color, String size, BigDecimal price, boolean active) { }