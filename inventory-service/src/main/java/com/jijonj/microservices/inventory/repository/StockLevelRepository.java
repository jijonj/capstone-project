package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.StockLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockLevelRepository extends JpaRepository<StockLevel, Long> {
    List<StockLevel> findByProductId(Long productId);
    List<StockLevel> findByWarehouseId(Long warehouseId);
}