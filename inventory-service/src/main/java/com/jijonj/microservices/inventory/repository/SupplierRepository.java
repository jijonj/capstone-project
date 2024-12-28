package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}