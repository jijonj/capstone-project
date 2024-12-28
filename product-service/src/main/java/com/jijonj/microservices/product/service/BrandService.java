package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.BrandRequest;
import com.jijonj.microservices.product.dto.BrandResponse;
import com.jijonj.microservices.product.model.Brand;
import com.jijonj.microservices.product.model.Product;
import com.jijonj.microservices.product.repository.BrandRepository;
import com.jijonj.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    public BrandResponse createBrand(BrandRequest brandRequest) {
        Brand brand = Brand.builder()
                .name(brandRequest.name())
                .description(brandRequest.description())
                .products(brandRequest.products())
                .build();
        brandRepository.save(brand);
        log.info("Brand created successfully");
        return mapToBrandResponse(brand);
    }

    public List<BrandResponse> getAllBrands(int page, int size) {
        Page<Brand> brands = brandRepository.findAll(PageRequest.of(page, size));
        return brands.stream().map(this::mapToBrandResponse).toList();
    }

    public List<BrandResponse> searchBrands(String query) {
        List<Brand> brands = brandRepository.findByNameContainingIgnoreCase(query);
        return brands.stream().map(this::mapToBrandResponse).toList();
    }

    public Optional<BrandResponse> getBrandById(String brandId) {
        return brandRepository.findById(brandId).map(this::mapToBrandResponse);
    }

    public Optional<BrandResponse> updateBrand(String brandId, BrandRequest brandRequest) {
        return brandRepository.findById(brandId).map(brand -> {
            brand.setName(brandRequest.name());
            brand.setDescription(brandRequest.description());
            brand.setProducts(brandRequest.products());
            brandRepository.save(brand);
            return mapToBrandResponse(brand);
        });
    }

    public Optional<BrandResponse> partialUpdateBrand(String brandId, BrandRequest brandRequest) {
        return brandRepository.findById(brandId).map(brand -> {
            if (brandRequest.name() != null) brand.setName(brandRequest.name());
            if (brandRequest.description() != null) brand.setDescription(brandRequest.description());
            if (brandRequest.products() != null) brand.setProducts(brandRequest.products());
            brandRepository.save(brand);
            return mapToBrandResponse(brand);
        });
    }

    public void deleteBrand(String brandId) {
        brandRepository.deleteById(brandId);
    }

    public List<BrandResponse> getProductsByBrandId(String brandId) {
        List<Product> products = productRepository.findByBrand(brandId);
        return products.stream()
                .map(product -> new BrandResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        List.of() // Assuming products are not relevant here
                ))
                .toList();
    }

    public List<BrandResponse> bulkCreateOrUpdateBrands(List<BrandRequest> brandRequests) {
        List<Brand> brands = brandRequests.stream().map(request -> Brand.builder()
                .name(request.name())
                .description(request.description())
                .products(request.products())
                .build()).toList();
        brandRepository.saveAll(brands);
        return brands.stream().map(this::mapToBrandResponse).toList();
    }

    public List<BrandResponse> getBrandHistory(String brandId) {
        // Implement logic to retrieve brand history
        return List.of(); // Placeholder
    }

    private BrandResponse mapToBrandResponse(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getName(), brand.getDescription(), brand.getProducts());
    }
}