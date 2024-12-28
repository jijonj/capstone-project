package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.ProductDiscountRequest;
import com.jijonj.microservices.product.dto.ProductDiscountResponse;
import com.jijonj.microservices.product.service.ProductDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/discounts")
@RequiredArgsConstructor
public class ProductDiscountController {

    private final ProductDiscountService productDiscountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDiscountResponse createProductDiscount(@RequestBody ProductDiscountRequest productDiscountRequest) {
        return productDiscountService.createProductDiscount(productDiscountRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDiscountResponse> getAllProductDiscounts(@RequestParam int page, @RequestParam int size) {
        return productDiscountService.getAllProductDiscounts(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDiscountResponse> searchProductDiscounts(@RequestParam String query) {
        return productDiscountService.searchProductDiscounts(query);
    }

    @GetMapping("/{discountId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductDiscountResponse> getProductDiscountById(@PathVariable String discountId) {
        return productDiscountService.getProductDiscountById(discountId);
    }

    @PutMapping("/{discountId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductDiscountResponse> updateProductDiscount(@PathVariable String discountId, @RequestBody ProductDiscountRequest productDiscountRequest) {
        return productDiscountService.updateProductDiscount(discountId, productDiscountRequest);
    }

    @PatchMapping("/{discountId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductDiscountResponse> partialUpdateProductDiscount(@PathVariable String discountId, @RequestBody ProductDiscountRequest productDiscountRequest) {
        return productDiscountService.partialUpdateProductDiscount(discountId, productDiscountRequest);
    }

    @DeleteMapping("/{discountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductDiscount(@PathVariable String discountId) {
        productDiscountService.deleteProductDiscount(discountId);
    }

    @GetMapping("/products/{productId}/discounts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDiscountResponse> getDiscountsByProductId(@PathVariable String productId) {
        return productDiscountService.getDiscountsByProductId(productId);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductDiscountResponse> bulkCreateOrUpdateProductDiscounts(@RequestBody List<ProductDiscountRequest> productDiscountRequests) {
        return productDiscountService.bulkCreateOrUpdateProductDiscounts(productDiscountRequests);
    }

    @PutMapping("/{discountId}/activate")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductDiscountResponse> activateProductDiscount(@PathVariable String discountId, @RequestParam boolean active) {
        return productDiscountService.activateProductDiscount(discountId, active);
    }
}