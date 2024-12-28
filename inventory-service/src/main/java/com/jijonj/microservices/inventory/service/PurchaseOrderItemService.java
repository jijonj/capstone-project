package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.PurchaseOrderItem;
import com.jijonj.microservices.inventory.repository.PurchaseOrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOrderItemService {
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;

    public List<PurchaseOrderItem> getAllPurchaseOrderItems(Long purchaseOrderId) {
        if (purchaseOrderId != null) {
            return purchaseOrderItemRepository.findByPurchaseOrderId(purchaseOrderId);
        } else {
            return purchaseOrderItemRepository.findAll();
        }
    }

    public PurchaseOrderItem getPurchaseOrderItemById(Long id) {
        Optional<PurchaseOrderItem> purchaseOrderItem = purchaseOrderItemRepository.findById(id);
        return purchaseOrderItem.orElse(null);
    }

    public PurchaseOrderItem createPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItemRepository.save(purchaseOrderItem);
    }

    public PurchaseOrderItem updatePurchaseOrderItem(Long id, PurchaseOrderItem purchaseOrderItem) {
        Optional<PurchaseOrderItem> existingPurchaseOrderItem = purchaseOrderItemRepository.findById(id);
        if (existingPurchaseOrderItem.isPresent()) {
            PurchaseOrderItem updatedPurchaseOrderItem = existingPurchaseOrderItem.get();
            updatedPurchaseOrderItem.setPurchaseOrderId(purchaseOrderItem.getPurchaseOrderId());
            updatedPurchaseOrderItem.setProductId(purchaseOrderItem.getProductId());
            updatedPurchaseOrderItem.setQuantityOrdered(purchaseOrderItem.getQuantityOrdered());
            updatedPurchaseOrderItem.setUnitPrice(purchaseOrderItem.getUnitPrice());
            updatedPurchaseOrderItem.setCreatedAt(purchaseOrderItem.getCreatedAt());
            updatedPurchaseOrderItem.setUpdatedAt(purchaseOrderItem.getUpdatedAt());
            return purchaseOrderItemRepository.save(updatedPurchaseOrderItem);
        } else {
            return null;
        }
    }

    public void deletePurchaseOrderItem(Long id) {
        purchaseOrderItemRepository.deleteById(id);
    }
}