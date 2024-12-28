package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.ProductHistoryRequest;
import com.jijonj.microservices.product.dto.ProductHistoryResponse;
import com.jijonj.microservices.product.model.ProductHistory;
import com.jijonj.microservices.product.repository.ProductHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductHistoryService {
    private final ProductHistoryRepository productHistoryRepository;

    public ProductHistoryResponse createProductHistory(ProductHistoryRequest productHistoryRequest) {
        ProductHistory productHistory = ProductHistory.builder()
                .productId(productHistoryRequest.productId())
                .actionType(productHistoryRequest.actionType())
                .description(productHistoryRequest.description())
                .actionDate(productHistoryRequest.actionDate())
                .build();
        productHistoryRepository.save(productHistory);
        log.info("Product history record created successfully");
        return mapToProductHistoryResponse(productHistory);
    }

    public List<ProductHistoryResponse> getAllProductHistories(int page, int size) {
        Page<ProductHistory> productHistories = productHistoryRepository.findAll(PageRequest.of(page, size));
        return productHistories.stream().map(this::mapToProductHistoryResponse).toList();
    }

    public List<ProductHistoryResponse> searchProductHistories(String query) {
        List<ProductHistory> productHistories = productHistoryRepository.findByActionTypeContainingIgnoreCase(query);
        return productHistories.stream().map(this::mapToProductHistoryResponse).toList();
    }

    public Optional<ProductHistoryResponse> getProductHistoryById(String historyId) {
        return productHistoryRepository.findById(historyId).map(this::mapToProductHistoryResponse);
    }

    public Optional<ProductHistoryResponse> updateProductHistory(String historyId, ProductHistoryRequest productHistoryRequest) {
        return productHistoryRepository.findById(historyId).map(productHistory -> {
            productHistory.setProductId(productHistoryRequest.productId());
            productHistory.setActionType(productHistoryRequest.actionType());
            productHistory.setDescription(productHistoryRequest.description());
            productHistory.setActionDate(productHistoryRequest.actionDate());
            productHistoryRepository.save(productHistory);
            return mapToProductHistoryResponse(productHistory);
        });
    }

    public Optional<ProductHistoryResponse> partialUpdateProductHistory(String historyId, ProductHistoryRequest productHistoryRequest) {
        return productHistoryRepository.findById(historyId).map(productHistory -> {
            if (productHistoryRequest.productId() != null) productHistory.setProductId(productHistoryRequest.productId());
            if (productHistoryRequest.actionType() != null) productHistory.setActionType(productHistoryRequest.actionType());
            if (productHistoryRequest.description() != null) productHistory.setDescription(productHistoryRequest.description());
            if (productHistoryRequest.actionDate() != null) productHistory.setActionDate(productHistoryRequest.actionDate());
            productHistoryRepository.save(productHistory);
            return mapToProductHistoryResponse(productHistory);
        });
    }

    public void deleteProductHistory(String historyId) {
        productHistoryRepository.deleteById(historyId);
    }

    public List<ProductHistoryResponse> getHistoriesByProductId(String productId) {
        List<ProductHistory> productHistories = productHistoryRepository.findByProductId(productId);
        return productHistories.stream().map(this::mapToProductHistoryResponse).toList();
    }

    public List<ProductHistoryResponse> bulkCreateOrUpdateProductHistories(List<ProductHistoryRequest> productHistoryRequests) {
        List<ProductHistory> productHistories = productHistoryRequests.stream().map(request -> ProductHistory.builder()
                .productId(request.productId())
                .actionType(request.actionType())
                .description(request.description())
                .actionDate(request.actionDate())
                .build()).toList();
        productHistoryRepository.saveAll(productHistories);
        return productHistories.stream().map(this::mapToProductHistoryResponse).toList();
    }

    private ProductHistoryResponse mapToProductHistoryResponse(ProductHistory productHistory) {
        return new ProductHistoryResponse(productHistory.getId(), productHistory.getProductId(), productHistory.getActionType(), productHistory.getDescription(), productHistory.getActionDate());
    }
}