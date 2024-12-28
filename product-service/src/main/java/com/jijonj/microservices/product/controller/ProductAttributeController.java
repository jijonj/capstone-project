package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.ProductAttributeRequest;
import com.jijonj.microservices.product.dto.ProductAttributeResponse;
import com.jijonj.microservices.product.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/product-attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductAttributeResponse createProductAttribute(@RequestBody ProductAttributeRequest productAttributeRequest) {
        return productAttributeService.createProductAttribute(productAttributeRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductAttributeResponse> getAllProductAttributes(@RequestParam int page, @RequestParam int size) {
        return productAttributeService.getAllProductAttributes(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductAttributeResponse> searchProductAttributes(@RequestParam String query) {
        return productAttributeService.searchProductAttributes(query);
    }

    @GetMapping("/{attributeId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductAttributeResponse> getProductAttributeById(@PathVariable String attributeId) {
        return productAttributeService.getProductAttributeById(attributeId);
    }

    @PutMapping("/{attributeId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductAttributeResponse> updateProductAttribute(@PathVariable String attributeId, @RequestBody ProductAttributeRequest productAttributeRequest) {
        return productAttributeService.updateProductAttribute(attributeId, productAttributeRequest);
    }

    @PatchMapping("/{attributeId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductAttributeResponse> partialUpdateProductAttribute(@PathVariable String attributeId, @RequestBody ProductAttributeRequest productAttributeRequest) {
        return productAttributeService.partialUpdateProductAttribute(attributeId, productAttributeRequest);
    }

    @DeleteMapping("/{attributeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductAttribute(@PathVariable String attributeId) {
        productAttributeService.deleteProductAttribute(attributeId);
    }

    @GetMapping("/products/{productId}/attributes")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductAttributeResponse> getAttributesByProductId(@PathVariable String productId) {
        return productAttributeService.getAttributesByProductId(productId);
    }

    @PostMapping("/products/{productId}/attributes")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductAttributeResponse> createAttributesForProduct(@PathVariable String productId, @RequestBody List<ProductAttributeRequest> productAttributeRequests) {
        productAttributeRequests.forEach(request -> request = new ProductAttributeRequest(productId, request.key(), request.value()));
        return productAttributeService.bulkCreateOrUpdateProductAttributes(productAttributeRequests);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductAttributeResponse> bulkCreateOrUpdateProductAttributes(@RequestBody List<ProductAttributeRequest> productAttributeRequests) {
        return productAttributeService.bulkCreateOrUpdateProductAttributes(productAttributeRequests);
    }
}