package com.jijonj.microservices.inventory.service;

import com.jijonj.microservices.inventory.model.TransferItem;
import com.jijonj.microservices.inventory.repository.TransferItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferItemService {
    private final TransferItemRepository transferItemRepository;

    public List<TransferItem> getAllTransferItems(Long inventoryTransferId) {
        if (inventoryTransferId != null) {
            return transferItemRepository.findByInventoryTransferId(inventoryTransferId);
        } else {
            return transferItemRepository.findAll();
        }
    }

    public TransferItem getTransferItemById(Long id) {
        Optional<TransferItem> transferItem = transferItemRepository.findById(id);
        return transferItem.orElse(null);
    }

    public TransferItem createTransferItem(TransferItem transferItem) {
        return transferItemRepository.save(transferItem);
    }

    public TransferItem updateTransferItem(Long id, TransferItem transferItem) {
        Optional<TransferItem> existingTransferItem = transferItemRepository.findById(id);
        if (existingTransferItem.isPresent()) {
            TransferItem updatedTransferItem = existingTransferItem.get();
            updatedTransferItem.setInventoryTransferId(transferItem.getInventoryTransferId());
            updatedTransferItem.setProductId(transferItem.getProductId());
            updatedTransferItem.setQuantity(transferItem.getQuantity());
            updatedTransferItem.setCreatedAt(transferItem.getCreatedAt());
            updatedTransferItem.setUpdatedAt(transferItem.getUpdatedAt());
            return transferItemRepository.save(updatedTransferItem);
        } else {
            return null;
        }
    }

    public void deleteTransferItem(Long id) {
        transferItemRepository.deleteById(id);
    }
}