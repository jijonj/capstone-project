package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}