package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.Warehouse;
import com.jijonj.microservices.inventory.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        return warehouse.orElse(null);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(id);
        if (existingWarehouse.isPresent()) {
            Warehouse updatedWarehouse = existingWarehouse.get();
            updatedWarehouse.setWarehouseName(warehouse.getWarehouseName());
            updatedWarehouse.setLocation(warehouse.getLocation());
            updatedWarehouse.setCreatedAt(warehouse.getCreatedAt());
            updatedWarehouse.setUpdatedAt(warehouse.getUpdatedAt());
            return warehouseRepository.save(updatedWarehouse);
        } else {
            return null;
        }
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}