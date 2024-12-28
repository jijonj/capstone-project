package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.BrandRequest;
import com.jijonj.microservices.product.dto.BrandResponse;
import com.jijonj.microservices.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandResponse createBrand(@RequestBody BrandRequest brandRequest) {
        return brandService.createBrand(brandRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponse> getAllBrands(@RequestParam int page, @RequestParam int size) {
        return brandService.getAllBrands(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponse> searchBrands(@RequestParam String query) {
        return brandService.searchBrands(query);
    }

    @GetMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<BrandResponse> getBrandById(@PathVariable String brandId) {
        return brandService.getBrandById(brandId);
    }

    @PutMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<BrandResponse> updateBrand(@PathVariable String brandId, @RequestBody BrandRequest brandRequest) {
        return brandService.updateBrand(brandId, brandRequest);
    }

    @PatchMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<BrandResponse> partialUpdateBrand(@PathVariable String brandId, @RequestBody BrandRequest brandRequest) {
        return brandService.partialUpdateBrand(brandId, brandRequest);
    }

    @DeleteMapping("/{brandId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable String brandId) {
        brandService.deleteBrand(brandId);
    }

    @GetMapping("/{brandId}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponse> getProductsByBrandId(@PathVariable String brandId) {
        return brandService.getProductsByBrandId(brandId);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<BrandResponse> bulkCreateOrUpdateBrands(@RequestBody List<BrandRequest> brandRequests) {
        return brandService.bulkCreateOrUpdateBrands(brandRequests);
    }

    @GetMapping("/{brandId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<BrandResponse> getBrandHistory(@PathVariable String brandId) {
        return brandService.getBrandHistory(brandId);
    }
}