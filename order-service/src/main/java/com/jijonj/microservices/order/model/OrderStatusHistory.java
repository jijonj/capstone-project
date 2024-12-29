package com.jijonj.microservices.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_status_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime changedAt;
    private String changedBy;
}