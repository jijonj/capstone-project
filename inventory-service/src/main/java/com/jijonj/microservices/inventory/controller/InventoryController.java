package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void setStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        inventoryService.setStock(skuCode, quantity);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewSku(@RequestParam String skuCode, @RequestParam Integer quantity) {
        inventoryService.addNewSku(skuCode, quantity);
    }
}
