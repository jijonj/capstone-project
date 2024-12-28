package com.jijonj.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "product_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductHistory {
    @Id
    private String id;
    private String productId;
    private String actionType;
    private String description;
    private LocalDateTime actionDate;
}