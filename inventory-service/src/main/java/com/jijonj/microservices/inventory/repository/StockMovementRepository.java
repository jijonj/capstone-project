package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}