package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.model.TransferItem;
import com.jijonj.microservices.inventory.service.TransferItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/transfer-items")
@RequiredArgsConstructor
public class TransferItemController {
    private final TransferItemService transferItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransferItem> getAllTransferItems(@RequestParam(required = false) Long inventoryTransferId) {
        return transferItemService.getAllTransferItems(inventoryTransferId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransferItem getTransferItemById(@PathVariable Long id) {
        return transferItemService.getTransferItemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransferItem createTransferItem(@RequestBody TransferItem transferItem) {
        return transferItemService.createTransferItem(transferItem);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransferItem updateTransferItem(@PathVariable Long id, @RequestBody TransferItem transferItem) {
        return transferItemService.updateTransferItem(id, transferItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransferItem(@PathVariable Long id) {
        transferItemService.deleteTransferItem(id);
    }
}