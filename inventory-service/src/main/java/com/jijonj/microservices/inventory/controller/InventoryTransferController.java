package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.model.InventoryTransfer;
import com.jijonj.microservices.inventory.service.InventoryTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/inventory/inventory-transfers")
@RequiredArgsConstructor
public class InventoryTransferController {
    private final InventoryTransferService inventoryTransferService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryTransfer> getAllInventoryTransfers(@RequestParam(required = false) Long fromWarehouseId,
                                                            @RequestParam(required = false) Long toWarehouseId,
                                                            @RequestParam(required = false) LocalDateTime startDate,
                                                            @RequestParam(required = false) LocalDateTime endDate,
                                                            @RequestParam(required = false) String status) {
        return inventoryTransferService.getAllInventoryTransfers(fromWarehouseId, toWarehouseId, startDate, endDate, status);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryTransfer getInventoryTransferById(@PathVariable Long id) {
        return inventoryTransferService.getInventoryTransferById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryTransfer createInventoryTransfer(@RequestBody InventoryTransfer inventoryTransfer) {
        return inventoryTransferService.createInventoryTransfer(inventoryTransfer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryTransfer updateInventoryTransfer(@PathVariable Long id, @RequestBody InventoryTransfer inventoryTransfer) {
        return inventoryTransferService.updateInventoryTransfer(id, inventoryTransfer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventoryTransfer(@PathVariable Long id) {
        inventoryTransferService.deleteInventoryTransfer(id);
    }
}