package com.jijonj.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Long orderId;
    private String paymentMethod;
    private String paymentStatus;
    private BigDecimal amountPaid;
    private String transactionId;
}

