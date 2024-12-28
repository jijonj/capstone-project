package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.ProductVariantRequest;
import com.jijonj.microservices.product.dto.ProductVariantResponse;
import com.jijonj.microservices.product.model.ProductVariant;
import com.jijonj.microservices.product.repository.ProductVariantRepository;
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
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;

    public ProductVariantResponse createProductVariant(ProductVariantRequest productVariantRequest) {
        ProductVariant productVariant = ProductVariant.builder()
                .productId(productVariantRequest.productId())
                .sku(productVariantRequest.sku())
                .color(productVariantRequest.color())
                .size(productVariantRequest.size())
                .price(productVariantRequest.price())
                .active(true)
                .build();
        productVariantRepository.save(productVariant);
        log.info("Product variant created successfully");
        return mapToProductVariantResponse(productVariant);
    }

    public List<ProductVariantResponse> getAllProductVariants(int page, int size) {
        Page<ProductVariant> productVariants = productVariantRepository.findAll(PageRequest.of(page, size));
        return productVariants.stream().map(this::mapToProductVariantResponse).toList();
    }

    public List<ProductVariantResponse> searchProductVariants(String query) {
        List<ProductVariant> productVariants = productVariantRepository.findBySkuContainingIgnoreCase(query);
        return productVariants.stream().map(this::mapToProductVariantResponse).toList();
    }

    public Optional<ProductVariantResponse> getProductVariantById(String variantId) {
        return productVariantRepository.findById(variantId).map(this::mapToProductVariantResponse);
    }

    public Optional<ProductVariantResponse> updateProductVariant(String variantId, ProductVariantRequest productVariantRequest) {
        return productVariantRepository.findById(variantId).map(productVariant -> {
            productVariant.setProductId(productVariantRequest.productId());
            productVariant.setSku(productVariantRequest.sku());
            productVariant.setColor(productVariantRequest.color());
            productVariant.setSize(productVariantRequest.size());
            productVariant.setPrice(productVariantRequest.price());
            productVariantRepository.save(productVariant);
            return mapToProductVariantResponse(productVariant);
        });
    }

    public Optional<ProductVariantResponse> partialUpdateProductVariant(String variantId, ProductVariantRequest productVariantRequest) {
        return productVariantRepository.findById(variantId).map(productVariant -> {
            if (productVariantRequest.productId() != null) productVariant.setProductId(productVariantRequest.productId());
            if (productVariantRequest.sku() != null) productVariant.setSku(productVariantRequest.sku());
            if (productVariantRequest.color() != null) productVariant.setColor(productVariantRequest.color());
            if (productVariantRequest.size() != null) productVariant.setSize(productVariantRequest.size());
            if (productVariantRequest.price() != null) productVariant.setPrice(productVariantRequest.price());
            productVariantRepository.save(productVariant);
            return mapToProductVariantResponse(productVariant);
        });
    }

    public void deleteProductVariant(String variantId) {
        productVariantRepository.deleteById(variantId);
    }

    public List<ProductVariantResponse> getVariantsByProductId(String productId) {
        List<ProductVariant> productVariants = productVariantRepository.findByProductId(productId);
        return productVariants.stream().map(this::mapToProductVariantResponse).toList();
    }

    public List<ProductVariantResponse> bulkCreateOrUpdateProductVariants(List<ProductVariantRequest> productVariantRequests) {
        List<ProductVariant> productVariants = productVariantRequests.stream().map(request -> ProductVariant.builder()
                .productId(request.productId())
                .sku(request.sku())
                .color(request.color())
                .size(request.size())
                .price(request.price())
                .active(true)
                .build()).toList();
        productVariantRepository.saveAll(productVariants);
        return productVariants.stream().map(this::mapToProductVariantResponse).toList();
    }

    private ProductVariantResponse mapToProductVariantResponse(ProductVariant productVariant) {
        return new ProductVariantResponse(productVariant.getId(), productVariant.getProductId(), productVariant.getSku(), productVariant.getColor(), productVariant.getSize(), productVariant.getPrice(), productVariant.isActive());
    }
}