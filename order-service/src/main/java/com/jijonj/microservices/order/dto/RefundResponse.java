package com.jijonj.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundResponse {
    private Long id;
    private Long orderId;
    private BigDecimal refundAmount;
    private String refundReason;
    private String processedBy;
    private LocalDateTime processedAt;
}