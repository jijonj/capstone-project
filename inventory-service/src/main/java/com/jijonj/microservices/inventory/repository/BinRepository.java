package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinRepository extends JpaRepository<Bin, Long> {
    List<Bin> findByWarehouseId(Long warehouseId);
}