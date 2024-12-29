package com.jijonj.microservices.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String invoiceNumber;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private BigDecimal totalAmount;
    private String status;
}