package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jijonj.microservices.inventory.model.Inventory;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
        log.info(" Start -- Received request to check stock for skuCode {}, with quantity {}", skuCode, quantity);
        boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
        log.info(" End -- Product with skuCode {}, and quantity {}, is in stock - {}", skuCode, quantity, isInStock);
        return isInStock;
    }
    public void setStock(String skuCode, Integer quantity) {
        log.info("Start -- Received request to set stock for skuCode {}, with quantity {}", skuCode, quantity);
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
        if (inventory != null) {
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
            log.info("End -- Product with skuCode {}, and quantity {}, is updated in stock", skuCode, quantity);
        } else {
            log.warn("End -- Product with skuCode {} not found, stock not updated", skuCode);
        }
    }
    public void addNewSku(String skuCode, Integer quantity) {
        log.info("Start -- Received request to add new SKU with skuCode {}, and quantity {}", skuCode, quantity);
        Inventory inventory = new Inventory();
        inventory.setSkuCode(skuCode);
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
        log.info("End -- New SKU with skuCode {}, and quantity {}, is added", skuCode, quantity);
    }
}
