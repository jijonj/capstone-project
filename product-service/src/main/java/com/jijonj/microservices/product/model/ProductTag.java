package com.jijonj.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTag {
    @Id
    private String id;
    private String productId;
    private String tagId;
}