package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}