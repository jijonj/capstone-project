package com.jijonj.microservices.product.dto;

import java.util.List;

public record BrandRequest(String name, String description, List<String> products) { }