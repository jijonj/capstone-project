package com.jijonj.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequest {
    private Long orderId;
    private String invoiceNumber;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private BigDecimal totalAmount;
    private String status;
}
