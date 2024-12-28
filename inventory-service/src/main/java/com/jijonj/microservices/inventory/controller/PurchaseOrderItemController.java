package com.jijonj.microservices.inventory.controller;

import com.jijonj.microservices.inventory.model.PurchaseOrderItem;
import com.jijonj.microservices.inventory.service.PurchaseOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/purchase-order-items")
@RequiredArgsConstructor
public class PurchaseOrderItemController {
    private final PurchaseOrderItemService purchaseOrderItemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseOrderItem> getAllPurchaseOrderItems(@RequestParam(required = false) Long purchaseOrderId) {
        return purchaseOrderItemService.getAllPurchaseOrderItems(purchaseOrderId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseOrderItem getPurchaseOrderItemById(@PathVariable Long id) {
        return purchaseOrderItemService.getPurchaseOrderItemById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrderItem createPurchaseOrderItem(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemService.createPurchaseOrderItem(purchaseOrderItem);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseOrderItem updatePurchaseOrderItem(@PathVariable Long id, @RequestBody PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemService.updatePurchaseOrderItem(id, purchaseOrderItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchaseOrderItem(@PathVariable Long id) {
        purchaseOrderItemService.deletePurchaseOrderItem(id);
    }
}