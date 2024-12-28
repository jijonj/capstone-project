package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.ProductRequest;
import com.jijonj.microservices.product.dto.ProductResponse;
import com.jijonj.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(@RequestParam int page, @RequestParam int size) {
        return productService.getAllProducts(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> searchProducts(@RequestParam String query) {
        return productService.searchProducts(query);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductResponse> getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @PatchMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductResponse> partialUpdateProduct(@PathVariable String productId, @RequestBody ProductRequest productRequest) {
        return productService.partialUpdateProduct(productId, productRequest);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }
}