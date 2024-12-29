package com.jijonj.microservices.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "refunds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private BigDecimal refundAmount;
    private String refundReason;
    private String processedBy;
    private LocalDateTime processedAt;
}