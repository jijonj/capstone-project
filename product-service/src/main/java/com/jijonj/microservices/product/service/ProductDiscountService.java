package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.ProductDiscountRequest;
import com.jijonj.microservices.product.dto.ProductDiscountResponse;
import com.jijonj.microservices.product.model.ProductDiscount;
import com.jijonj.microservices.product.repository.ProductDiscountRepository;
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
public class ProductDiscountService {
    private final ProductDiscountRepository productDiscountRepository;

    public ProductDiscountResponse createProductDiscount(ProductDiscountRequest productDiscountRequest) {
        ProductDiscount productDiscount = ProductDiscount.builder()
                .productId(productDiscountRequest.productId())
                .type(productDiscountRequest.type())
                .discountValue(productDiscountRequest.discountValue())
                .startDate(productDiscountRequest.startDate())
                .endDate(productDiscountRequest.endDate())
                .active(true)
                .build();
        productDiscountRepository.save(productDiscount);
        log.info("Product discount created successfully");
        return mapToProductDiscountResponse(productDiscount);
    }

    public List<ProductDiscountResponse> getAllProductDiscounts(int page, int size) {
        Page<ProductDiscount> productDiscounts = productDiscountRepository.findAll(PageRequest.of(page, size));
        return productDiscounts.stream().map(this::mapToProductDiscountResponse).toList();
    }

    public List<ProductDiscountResponse> searchProductDiscounts(String query) {
        List<ProductDiscount> productDiscounts = productDiscountRepository.findByTypeContainingIgnoreCase(query);
        return productDiscounts.stream().map(this::mapToProductDiscountResponse).toList();
    }

    public Optional<ProductDiscountResponse> getProductDiscountById(String discountId) {
        return productDiscountRepository.findById(discountId).map(this::mapToProductDiscountResponse);
    }

    public Optional<ProductDiscountResponse> updateProductDiscount(String discountId, ProductDiscountRequest productDiscountRequest) {
        return productDiscountRepository.findById(discountId).map(productDiscount -> {
            productDiscount.setProductId(productDiscountRequest.productId());
            productDiscount.setType(productDiscountRequest.type());
            productDiscount.setDiscountValue(productDiscountRequest.discountValue());
            productDiscount.setStartDate(productDiscountRequest.startDate());
            productDiscount.setEndDate(productDiscountRequest.endDate());
            productDiscountRepository.save(productDiscount);
            return mapToProductDiscountResponse(productDiscount);
        });
    }

    public Optional<ProductDiscountResponse> partialUpdateProductDiscount(String discountId, ProductDiscountRequest productDiscountRequest) {
        return productDiscountRepository.findById(discountId).map(productDiscount -> {
            if (productDiscountRequest.productId() != null) productDiscount.setProductId(productDiscountRequest.productId());
            if (productDiscountRequest.type() != null) productDiscount.setType(productDiscountRequest.type());
            if (productDiscountRequest.discountValue() != 0) productDiscount.setDiscountValue(productDiscountRequest.discountValue());
            if (productDiscountRequest.startDate() != null) productDiscount.setStartDate(productDiscountRequest.startDate());
            if (productDiscountRequest.endDate() != null) productDiscount.setEndDate(productDiscountRequest.endDate());
            productDiscountRepository.save(productDiscount);
            return mapToProductDiscountResponse(productDiscount);
        });
    }

    public void deleteProductDiscount(String discountId) {
        productDiscountRepository.deleteById(discountId);
    }

    public List<ProductDiscountResponse> getDiscountsByProductId(String productId) {
        List<ProductDiscount> productDiscounts = productDiscountRepository.findByProductId(productId);
        return productDiscounts.stream().map(this::mapToProductDiscountResponse).toList();
    }

    public List<ProductDiscountResponse> bulkCreateOrUpdateProductDiscounts(List<ProductDiscountRequest> productDiscountRequests) {
        List<ProductDiscount> productDiscounts = productDiscountRequests.stream().map(request -> ProductDiscount.builder()
                .productId(request.productId())
                .type(request.type())
                .discountValue(request.discountValue())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .active(true)
                .build()).toList();
        productDiscountRepository.saveAll(productDiscounts);
        return productDiscounts.stream().map(this::mapToProductDiscountResponse).toList();
    }

    public Optional<ProductDiscountResponse> activateProductDiscount(String discountId, boolean active) {
        return productDiscountRepository.findById(discountId).map(productDiscount -> {
            productDiscount.setActive(active);
            productDiscountRepository.save(productDiscount);
            return mapToProductDiscountResponse(productDiscount);
        });
    }

    private ProductDiscountResponse mapToProductDiscountResponse(ProductDiscount productDiscount) {
        return new ProductDiscountResponse(productDiscount.getId(), productDiscount.getProductId(), productDiscount.getType(), productDiscount.getDiscountValue(), productDiscount.getStartDate(), productDiscount.getEndDate(), productDiscount.isActive());
    }
}