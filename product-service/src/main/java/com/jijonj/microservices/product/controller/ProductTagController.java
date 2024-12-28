package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.ProductTagRequest;
import com.jijonj.microservices.product.dto.ProductTagResponse;
import com.jijonj.microservices.product.service.ProductTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/product-tags")
@RequiredArgsConstructor
public class ProductTagController {

    private final ProductTagService productTagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductTagResponse createProductTag(@RequestBody ProductTagRequest productTagRequest) {
        return productTagService.createProductTag(productTagRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductTagResponse> getAllProductTags(@RequestParam int page, @RequestParam int size) {
        return productTagService.getAllProductTags(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductTagResponse> searchProductTags(@RequestParam String query) {
        return productTagService.searchProductTags(query);
    }

    @GetMapping("/{mappingId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductTagResponse> getProductTagById(@PathVariable String mappingId) {
        return productTagService.getProductTagById(mappingId);
    }

    @PutMapping("/{mappingId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductTagResponse> updateProductTag(@PathVariable String mappingId, @RequestBody ProductTagRequest productTagRequest) {
        return productTagService.updateProductTag(mappingId, productTagRequest);
    }

    @PatchMapping("/{mappingId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductTagResponse> partialUpdateProductTag(@PathVariable String mappingId, @RequestBody ProductTagRequest productTagRequest) {
        return productTagService.partialUpdateProductTag(mappingId, productTagRequest);
    }

    @DeleteMapping("/{mappingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductTag(@PathVariable String mappingId) {
        productTagService.deleteProductTag(mappingId);
    }

    @GetMapping("/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductTagResponse> getTagsByProductId(@PathVariable String productId) {
        return productTagService.getTagsByProductId(productId);
    }

    @GetMapping("/tag/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductTagResponse> getProductsByTagId(@PathVariable String tagId) {
        return productTagService.getProductsByTagId(tagId);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ProductTagResponse> bulkCreateOrUpdateProductTags(@RequestBody List<ProductTagRequest> productTagRequests) {
        return productTagService.bulkCreateOrUpdateProductTags(productTagRequests);
    }
}