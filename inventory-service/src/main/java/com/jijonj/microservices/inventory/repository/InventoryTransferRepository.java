package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.InventoryTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoryTransferRepository extends JpaRepository<InventoryTransfer, Long> {
    List<InventoryTransfer> findByFromWarehouseId(Long fromWarehouseId);
    List<InventoryTransfer> findByToWarehouseId(Long toWarehouseId);
    List<InventoryTransfer> findByTransferDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<InventoryTransfer> findByStatus(String status);
}