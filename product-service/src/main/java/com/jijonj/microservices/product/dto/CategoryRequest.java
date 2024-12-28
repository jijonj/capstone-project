package com.jijonj.microservices.product.dto;

import java.util.List;

public record CategoryRequest(String name, String description, List<String> subCategories) { }