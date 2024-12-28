package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.ProductHistoryRequest;
import com.jijonj.microservices.product.dto.ProductHistoryResponse;
import com.jijonj.microservices.product.service.ProductHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/product-history")
@RequiredArgsConstructor
public class ProductHistoryController {

    private final ProductHistoryService productHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductHistoryResponse createProductHistory(@RequestBody ProductHistoryRequest productHistoryRequest) {
        return productHistoryService.createProductHistory(productHistoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHistoryResponse> getAllProductHistories(@RequestParam int page, @RequestParam int size) {
        return productHistoryService.getAllProductHistories(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHistoryResponse> searchProductHistories(@RequestParam String query) {
        return productHistoryService.searchProductHistories(query);
    }

    @GetMapping("/{historyId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductHistoryResponse> getProductHistoryById(@PathVariable String historyId) {
        return productHistoryService.getProductHistoryById(historyId);
    }

    @PutMapping("/{historyId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductHistoryResponse> updateProductHistory(@PathVariable String historyId, @RequestBody ProductHistoryRequest productHistoryRequest) {
        return productHistoryService.updateProductHistory(historyId, productHistoryRequest);
    }

    @PatchMapping("/{historyId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductHistoryResponse> partialUpdateProductHistory(@PathVariable String historyId, @RequestBody ProductHistoryRequest productHistoryRequest) {
        return productHistoryService.partialUpdateProductHistory(historyId, productHistoryRequest);
    }

    @DeleteMapping("/{historyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductHistory(@PathVariable String historyId) {
        productHistoryService.deleteProductHistory(historyId);
    }

    @GetMapping("/products/{productId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHistoryResponse> getHistoriesByProductId(@PathVariable String productId) {
        return productHistoryService.getHistoriesByProductId(productId);
    }

    @PostMapping("/products/{productId}/history")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductHistoryResponse createHistoryForProduct(@PathVariable String productId, @RequestBody ProductHistoryRequest productHistoryRequest) {
        productHistoryRequest = new ProductHistoryRequest(productId, productHistoryRequest.actionType(), productHistoryRequest.description(), productHistoryRequest.actionDate());
        return productHistoryService.createProductHistory(productHistoryRequest);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductHistoryResponse> bulkCreateOrUpdateProductHistories(@RequestBody List<ProductHistoryRequest> productHistoryRequests) {
        return productHistoryService.bulkCreateOrUpdateProductHistories(productHistoryRequests);
    }
}