package com.jijonj.microservices.inventory.repository;

import com.jijonj.microservices.inventory.model.TransferItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferItemRepository extends JpaRepository<TransferItem, Long> {
    List<TransferItem> findByInventoryTransferId(Long inventoryTransferId);
}