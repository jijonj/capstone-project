package com.jijonj.microservices.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_levels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long warehouseId;
    private Long binId;
    private int quantityOnHand;
    private int quantityReserved;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}