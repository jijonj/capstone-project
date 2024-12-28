package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.StockMovement;
import com.jijonj.microservices.inventory.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockMovementService {
    private final StockMovementRepository stockMovementRepository;

    public List<StockMovement> getAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    public StockMovement getStockMovementById(Long id) {
        Optional<StockMovement> stockMovement = stockMovementRepository.findById(id);
        return stockMovement.orElse(null);
    }

    public StockMovement createStockMovement(StockMovement stockMovement) {
        return stockMovementRepository.save(stockMovement);
    }

    public StockMovement updateStockMovement(Long id, StockMovement stockMovement) {
        Optional<StockMovement> existingStockMovement = stockMovementRepository.findById(id);
        if (existingStockMovement.isPresent()) {
            StockMovement updatedStockMovement = existingStockMovement.get();
            updatedStockMovement.setProductId(stockMovement.getProductId());
            updatedStockMovement.setWarehouseId(stockMovement.getWarehouseId());
            updatedStockMovement.setBinId(stockMovement.getBinId());
            updatedStockMovement.setMovementType(stockMovement.getMovementType());
            updatedStockMovement.setQuantity(stockMovement.getQuantity());
            updatedStockMovement.setMovementDate(stockMovement.getMovementDate());
            updatedStockMovement.setReferenceDoc(stockMovement.getReferenceDoc());
            updatedStockMovement.setCreatedAt(stockMovement.getCreatedAt());
            updatedStockMovement.setUpdatedAt(stockMovement.getUpdatedAt());
            return stockMovementRepository.save(updatedStockMovement);
        } else {
            return null;
        }
    }

    public void deleteStockMovement(Long id) {
        stockMovementRepository.deleteById(id);
    }
}