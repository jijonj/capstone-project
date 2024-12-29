package com.jijonj.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusHistoryRequest {
    private Long orderId;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime changedAt;
    private String changedBy;
}