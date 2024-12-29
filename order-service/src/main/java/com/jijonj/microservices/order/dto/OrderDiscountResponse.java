package com.jijonj.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDiscountResponse {
    private Long id;
    private Long orderId;
    private Long discountId;
    private BigDecimal discountAmount;
    private LocalDateTime appliedAt;
}