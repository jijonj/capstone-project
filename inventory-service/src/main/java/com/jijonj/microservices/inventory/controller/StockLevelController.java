package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.model.StockLevel;
import com.jijonj.microservices.inventory.service.StockLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/stock-levels")
@RequiredArgsConstructor
public class StockLevelController {
    private final StockLevelService stockLevelService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockLevel> getAllStockLevels(@RequestParam(required = false) Long productId,
                                              @RequestParam(required = false) Long warehouseId) {
        return stockLevelService.getAllStockLevels(productId, warehouseId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockLevel getStockLevelById(@PathVariable Long id) {
        return stockLevelService.getStockLevelById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockLevel createStockLevel(@RequestBody StockLevel stockLevel) {
        return stockLevelService.createStockLevel(stockLevel);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StockLevel updateStockLevel(@PathVariable Long id, @RequestBody StockLevel stockLevel) {
        return stockLevelService.updateStockLevel(id, stockLevel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStockLevel(@PathVariable Long id) {
        stockLevelService.deleteStockLevel(id);
    }
}