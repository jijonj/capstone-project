package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.ProductTagRequest;
import com.jijonj.microservices.product.dto.ProductTagResponse;
import com.jijonj.microservices.product.model.ProductTag;
import com.jijonj.microservices.product.repository.ProductTagRepository;
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
public class ProductTagService {
    private final ProductTagRepository productTagRepository;

    public ProductTagResponse createProductTag(ProductTagRequest productTagRequest) {
        ProductTag productTag = ProductTag.builder()
                .productId(productTagRequest.productId())
                .tagId(productTagRequest.tagId())
                .build();
        productTagRepository.save(productTag);
        log.info("Product tag mapping created successfully");
        return mapToProductTagResponse(productTag);
    }

    public List<ProductTagResponse> getAllProductTags(int page, int size) {
        Page<ProductTag> productTags = productTagRepository.findAll(PageRequest.of(page, size));
        return productTags.stream().map(this::mapToProductTagResponse).toList();
    }

    public List<ProductTagResponse> searchProductTags(String query) {
        List<ProductTag> productTags = productTagRepository.findByProductId(query);
        return productTags.stream().map(this::mapToProductTagResponse).toList();
    }

    public Optional<ProductTagResponse> getProductTagById(String mappingId) {
        return productTagRepository.findById(mappingId).map(this::mapToProductTagResponse);
    }

    public Optional<ProductTagResponse> updateProductTag(String mappingId, ProductTagRequest productTagRequest) {
        return productTagRepository.findById(mappingId).map(productTag -> {
            productTag.setProductId(productTagRequest.productId());
            productTag.setTagId(productTagRequest.tagId());
            productTagRepository.save(productTag);
            return mapToProductTagResponse(productTag);
        });
    }

    public Optional<ProductTagResponse> partialUpdateProductTag(String mappingId, ProductTagRequest productTagRequest) {
        return productTagRepository.findById(mappingId).map(productTag -> {
            if (productTagRequest.productId() != null) productTag.setProductId(productTagRequest.productId());
            if (productTagRequest.tagId() != null) productTag.setTagId(productTagRequest.tagId());
            productTagRepository.save(productTag);
            return mapToProductTagResponse(productTag);
        });
    }

    public void deleteProductTag(String mappingId) {
        productTagRepository.deleteById(mappingId);
    }

    public List<ProductTagResponse> getTagsByProductId(String productId) {
        List<ProductTag> productTags = productTagRepository.findByProductId(productId);
        return productTags.stream().map(this::mapToProductTagResponse).toList();
    }

    public List<ProductTagResponse> getProductsByTagId(String tagId) {
        List<ProductTag> productTags = productTagRepository.findByTagId(tagId);
        return productTags.stream().map(this::mapToProductTagResponse).toList();
    }

    public List<ProductTagResponse> bulkCreateOrUpdateProductTags(List<ProductTagRequest> productTagRequests) {
        List<ProductTag> productTags = productTagRequests.stream().map(request -> ProductTag.builder()
                .productId(request.productId())
                .tagId(request.tagId())
                .build()).toList();
        productTagRepository.saveAll(productTags);
        return productTags.stream().map(this::mapToProductTagResponse).toList();
    }

    private ProductTagResponse mapToProductTagResponse(ProductTag productTag) {
        return new ProductTagResponse(productTag.getId(), productTag.getProductId(), productTag.getTagId());
    }
}