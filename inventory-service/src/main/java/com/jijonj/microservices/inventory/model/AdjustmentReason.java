package com.jijonj.microservices.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "adjustment_reasons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdjustmentReason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reasonCode;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}