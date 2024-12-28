package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.ProductAttributeRequest;
import com.jijonj.microservices.product.dto.ProductAttributeResponse;
import com.jijonj.microservices.product.model.ProductAttribute;
import com.jijonj.microservices.product.repository.ProductAttributeRepository;
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
public class ProductAttributeService {
    private final ProductAttributeRepository productAttributeRepository;

    public ProductAttributeResponse createProductAttribute(ProductAttributeRequest productAttributeRequest) {
        ProductAttribute productAttribute = ProductAttribute.builder()
                .productId(productAttributeRequest.productId())
                .key(productAttributeRequest.key())
                .value(productAttributeRequest.value())
                .build();
        productAttributeRepository.save(productAttribute);
        log.info("Product attribute created successfully");
        return mapToProductAttributeResponse(productAttribute);
    }

    public List<ProductAttributeResponse> getAllProductAttributes(int page, int size) {
        Page<ProductAttribute> productAttributes = productAttributeRepository.findAll(PageRequest.of(page, size));
        return productAttributes.stream().map(this::mapToProductAttributeResponse).toList();
    }

    public List<ProductAttributeResponse> searchProductAttributes(String query) {
        List<ProductAttribute> productAttributes = productAttributeRepository.findByKeyContainingIgnoreCaseOrValueContainingIgnoreCase(query, query);
        return productAttributes.stream().map(this::mapToProductAttributeResponse).toList();
    }

    public Optional<ProductAttributeResponse> getProductAttributeById(String attributeId) {
        return productAttributeRepository.findById(attributeId).map(this::mapToProductAttributeResponse);
    }

    public Optional<ProductAttributeResponse> updateProductAttribute(String attributeId, ProductAttributeRequest productAttributeRequest) {
        return productAttributeRepository.findById(attributeId).map(productAttribute -> {
            productAttribute.setProductId(productAttributeRequest.productId());
            productAttribute.setKey(productAttributeRequest.key());
            productAttribute.setValue(productAttributeRequest.value());
            productAttributeRepository.save(productAttribute);
            return mapToProductAttributeResponse(productAttribute);
        });
    }

    public Optional<ProductAttributeResponse> partialUpdateProductAttribute(String attributeId, ProductAttributeRequest productAttributeRequest) {
        return productAttributeRepository.findById(attributeId).map(productAttribute -> {
            if (productAttributeRequest.productId() != null) productAttribute.setProductId(productAttributeRequest.productId());
            if (productAttributeRequest.key() != null) productAttribute.setKey(productAttributeRequest.key());
            if (productAttributeRequest.value() != null) productAttribute.setValue(productAttributeRequest.value());
            productAttributeRepository.save(productAttribute);
            return mapToProductAttributeResponse(productAttribute);
        });
    }

    public void deleteProductAttribute(String attributeId) {
        productAttributeRepository.deleteById(attributeId);
    }

    public List<ProductAttributeResponse> getAttributesByProductId(String productId) {
        List<ProductAttribute> productAttributes = productAttributeRepository.findByProductId(productId);
        return productAttributes.stream().map(this::mapToProductAttributeResponse).toList();
    }

    public List<ProductAttributeResponse> bulkCreateOrUpdateProductAttributes(List<ProductAttributeRequest> productAttributeRequests) {
        List<ProductAttribute> productAttributes = productAttributeRequests.stream().map(request -> ProductAttribute.builder()
                .productId(request.productId())
                .key(request.key())
                .value(request.value())
                .build()).toList();
        productAttributeRepository.saveAll(productAttributes);
        return productAttributes.stream().map(this::mapToProductAttributeResponse).toList();
    }

    private ProductAttributeResponse mapToProductAttributeResponse(ProductAttribute productAttribute) {
        return new ProductAttributeResponse(productAttribute.getId(), productAttribute.getProductId(), productAttribute.getKey(), productAttribute.getValue());
    }
}