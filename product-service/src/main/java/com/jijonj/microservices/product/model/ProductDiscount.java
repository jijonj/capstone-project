package com.jijonj.microservices.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "product_discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDiscount {
    @Id
    private String id;
    private String productId;
    private String type;
    private double discountValue;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}