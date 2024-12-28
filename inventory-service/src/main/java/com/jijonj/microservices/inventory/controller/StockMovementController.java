package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.model.StockMovement;
import com.jijonj.microservices.inventory.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {
    private final StockMovementService stockMovementService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockMovement> getAllStockMovements() {
        return stockMovementService.getAllStockMovements();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockMovement getStockMovementById(@PathVariable Long id) {
        return stockMovementService.getStockMovementById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockMovement createStockMovement(@RequestBody StockMovement stockMovement) {
        return stockMovementService.createStockMovement(stockMovement);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockMovement updateStockMovement(@PathVariable Long id, @RequestBody StockMovement stockMovement) {
        return stockMovementService.updateStockMovement(id, stockMovement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStockMovement(@PathVariable Long id) {
        stockMovementService.deleteStockMovement(id);
    }
}