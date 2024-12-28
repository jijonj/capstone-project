package com.jijonj.microservices.product.dto;

import java.util.List;

public record BrandResponse(String id, String name, String description, List<String> products) { }