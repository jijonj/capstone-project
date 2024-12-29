package com.jijonj.microservices.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_discounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long discountId;
    private BigDecimal discountAmount;
    private LocalDateTime appliedAt;
}