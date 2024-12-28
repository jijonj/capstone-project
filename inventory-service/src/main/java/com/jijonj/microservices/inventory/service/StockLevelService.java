package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.StockLevel;
import com.jijonj.microservices.inventory.repository.StockLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockLevelService {
    private final StockLevelRepository stockLevelRepository;

    public List<StockLevel> getAllStockLevels(Long productId, Long warehouseId) {
        if (productId != null) {
            return stockLevelRepository.findByProductId(productId);
        } else if (warehouseId != null) {
            return stockLevelRepository.findByWarehouseId(warehouseId);
        } else {
            return stockLevelRepository.findAll();
        }
    }

    public StockLevel getStockLevelById(Long id) {
        Optional<StockLevel> stockLevel = stockLevelRepository.findById(id);
        return stockLevel.orElse(null);
    }

    public StockLevel createStockLevel(StockLevel stockLevel) {
        return stockLevelRepository.save(stockLevel);
    }

    public StockLevel updateStockLevel(Long id, StockLevel stockLevel) {
        Optional<StockLevel> existingStockLevel = stockLevelRepository.findById(id);
        if (existingStockLevel.isPresent()) {
            StockLevel updatedStockLevel = existingStockLevel.get();
            updatedStockLevel.setProductId(stockLevel.getProductId());
            updatedStockLevel.setWarehouseId(stockLevel.getWarehouseId());
            updatedStockLevel.setBinId(stockLevel.getBinId());
            updatedStockLevel.setQuantityOnHand(stockLevel.getQuantityOnHand());
            updatedStockLevel.setQuantityReserved(stockLevel.getQuantityReserved());
            updatedStockLevel.setCreatedAt(stockLevel.getCreatedAt());
            updatedStockLevel.setUpdatedAt(stockLevel.getUpdatedAt());
            return stockLevelRepository.save(updatedStockLevel);
        } else {
            return null;
        }
    }

    public void deleteStockLevel(Long id) {
        stockLevelRepository.deleteById(id);
    }
}