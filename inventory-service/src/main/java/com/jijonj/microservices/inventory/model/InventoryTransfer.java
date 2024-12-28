package com.jijonj.microservices.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_transfers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromWarehouseId;
    private Long toWarehouseId;
    private LocalDateTime transferDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}