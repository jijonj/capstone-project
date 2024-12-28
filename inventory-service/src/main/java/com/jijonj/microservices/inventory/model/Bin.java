package com.jijonj.microservices.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long warehouseId;
    private String binName;
    private String binType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}