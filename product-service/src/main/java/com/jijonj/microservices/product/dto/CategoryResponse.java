package com.jijonj.microservices.product.dto;

import java.util.List;

public record CategoryResponse(String id, String name, String description, List<String> subCategories) { }