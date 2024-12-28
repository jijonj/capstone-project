package com.jijonj.microservices.product.dto;

import java.math.BigDecimal;

public record ProductVariantRequest(String productId, String sku, String color, String size, BigDecimal price) { }