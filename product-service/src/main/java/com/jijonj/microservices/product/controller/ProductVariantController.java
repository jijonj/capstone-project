package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.ProductVariantRequest;
import com.jijonj.microservices.product.dto.ProductVariantResponse;
import com.jijonj.microservices.product.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductVariantResponse createProductVariant(@RequestBody ProductVariantRequest productVariantRequest) {
        return productVariantService.createProductVariant(productVariantRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductVariantResponse> getAllProductVariants(@RequestParam int page, @RequestParam int size) {
        return productVariantService.getAllProductVariants(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductVariantResponse> searchProductVariants(@RequestParam String query) {
        return productVariantService.searchProductVariants(query);
    }

    @GetMapping("/{variantId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductVariantResponse> getProductVariantById(@PathVariable String variantId) {
        return productVariantService.getProductVariantById(variantId);
    }

    @PutMapping("/{variantId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductVariantResponse> updateProductVariant(@PathVariable String variantId, @RequestBody ProductVariantRequest productVariantRequest) {
        return productVariantService.updateProductVariant(variantId, productVariantRequest);
    }

    @PatchMapping("/{variantId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductVariantResponse> partialUpdateProductVariant(@PathVariable String variantId, @RequestBody ProductVariantRequest productVariantRequest) {
        return productVariantService.partialUpdateProductVariant(variantId, productVariantRequest);
    }

    @DeleteMapping("/{variantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductVariant(@PathVariable String variantId) {
        productVariantService.deleteProductVariant(variantId);
    }

    @GetMapping("/products/{productId}/variants")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductVariantResponse> getVariantsByProductId(@PathVariable String productId) {
        return productVariantService.getVariantsByProductId(productId);
    }

    @PostMapping("/products/{productId}/variants")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductVariantResponse> createVariantsForProduct(@PathVariable String productId, @RequestBody List<ProductVariantRequest> productVariantRequests) {
        productVariantRequests.forEach(request -> request = new ProductVariantRequest(productId, request.sku(), request.color(), request.size(), request.price()));
        return productVariantService.bulkCreateOrUpdateProductVariants(productVariantRequests);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductVariantResponse> bulkCreateOrUpdateProductVariants(@RequestBody List<ProductVariantRequest> productVariantRequests) {
        return productVariantService.bulkCreateOrUpdateProductVariants(productVariantRequests);
    }
}