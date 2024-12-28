package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.InventoryTransfer;
import com.jijonj.microservices.inventory.repository.InventoryTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryTransferService {
    private final InventoryTransferRepository inventoryTransferRepository;

    public List<InventoryTransfer> getAllInventoryTransfers(Long fromWarehouseId, Long toWarehouseId, LocalDateTime startDate, LocalDateTime endDate, String status) {
        if (fromWarehouseId != null) {
            return inventoryTransferRepository.findByFromWarehouseId(fromWarehouseId);
        } else if (toWarehouseId != null) {
            return inventoryTransferRepository.findByToWarehouseId(toWarehouseId);
        } else if (startDate != null && endDate != null) {
            return inventoryTransferRepository.findByTransferDateBetween(startDate, endDate);
        } else if (status != null) {
            return inventoryTransferRepository.findByStatus(status);
        } else {
            return inventoryTransferRepository.findAll();
        }
    }

    public InventoryTransfer getInventoryTransferById(Long id) {
        Optional<InventoryTransfer> inventoryTransfer = inventoryTransferRepository.findById(id);
        return inventoryTransfer.orElse(null);
    }

    public InventoryTransfer createInventoryTransfer(InventoryTransfer inventoryTransfer) {
        return inventoryTransferRepository.save(inventoryTransfer);
    }

    public InventoryTransfer updateInventoryTransfer(Long id, InventoryTransfer inventoryTransfer) {
        Optional<InventoryTransfer> existingInventoryTransfer = inventoryTransferRepository.findById(id);
        if (existingInventoryTransfer.isPresent()) {
            InventoryTransfer updatedInventoryTransfer = existingInventoryTransfer.get();
            updatedInventoryTransfer.setFromWarehouseId(inventoryTransfer.getFromWarehouseId());
            updatedInventoryTransfer.setToWarehouseId(inventoryTransfer.getToWarehouseId());
            updatedInventoryTransfer.setTransferDate(inventoryTransfer.getTransferDate());
            updatedInventoryTransfer.setStatus(inventoryTransfer.getStatus());
            updatedInventoryTransfer.setCreatedAt(inventoryTransfer.getCreatedAt());
            updatedInventoryTransfer.setUpdatedAt(inventoryTransfer.getUpdatedAt());
            return inventoryTransferRepository.save(updatedInventoryTransfer);
        } else {
            return null;
        }
    }

    public void deleteInventoryTransfer(Long id) {
        inventoryTransferRepository.deleteById(id);
    }
}