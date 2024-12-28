package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.ProductRequest;
import com.jijonj.microservices.product.dto.ProductResponse;
import com.jijonj.microservices.product.model.Product;
import com.jijonj.microservices.product.repository.ProductRepository;
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
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .brand(productRequest.brand())
                .category(productRequest.category())
                .description(productRequest.description())
                .skuCode(productRequest.skuCode())
                .price(productRequest.price())
                .active(true)
                .build();
        productRepository.save(product);
        log.info("Product created successfully");
        return mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts(int page, int size) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public List<ProductResponse> searchProducts(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query, query);
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public Optional<ProductResponse> getProductById(String productId) {
        return productRepository.findById(productId).map(this::mapToProductResponse);
    }

    public Optional<ProductResponse> updateProduct(String productId, ProductRequest productRequest) {
        return productRepository.findById(productId).map(product -> {
            product.setName(productRequest.name());
            product.setBrand(productRequest.brand());
            product.setCategory(productRequest.category());
            product.setDescription(productRequest.description());
            product.setSkuCode(productRequest.skuCode());
            product.setPrice(productRequest.price());
            productRepository.save(product);
            return mapToProductResponse(product);
        });
    }

    public Optional<ProductResponse> partialUpdateProduct(String productId, ProductRequest productRequest) {
        return productRepository.findById(productId).map(product -> {
            if (productRequest.name() != null) product.setName(productRequest.name());
            if (productRequest.brand() != null) product.setBrand(productRequest.brand());
            if (productRequest.category() != null) product.setCategory(productRequest.category());
            if (productRequest.description() != null) product.setDescription(productRequest.description());
            if (productRequest.skuCode() != null) product.setSkuCode(productRequest.skuCode());
            if (productRequest.price() != null) product.setPrice(productRequest.price());
            productRepository.save(product);
            return mapToProductResponse(product);
        });
    }

    public void deleteProduct(String productId) {
        productRepository.findById(productId).ifPresent(product -> {
            product.setActive(false);
            productRepository.save(product);
        });
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getBrand(), product.getCategory(),
                product.getDescription(), product.getSkuCode(), product.getPrice(), product.isActive());
    }
}