package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.CategoryRequest;
import com.jijonj.microservices.product.dto.CategoryResponse;
import com.jijonj.microservices.product.model.Category;
import com.jijonj.microservices.product.model.Product;
import com.jijonj.microservices.product.repository.CategoryRepository;
import com.jijonj.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .subCategories(categoryRequest.subCategories())
                .build();
        categoryRepository.save(category);
        log.info("Category created successfully");
        return mapToCategoryResponse(category);
    }

    public List<CategoryResponse> getAllCategories(int page, int size) {
        Page<Category> categories = categoryRepository.findAll(PageRequest.of(page, size));
        return categories.stream().map(this::mapToCategoryResponse).toList();
    }

    public List<CategoryResponse> searchCategories(String query) {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(query);
        return categories.stream().map(this::mapToCategoryResponse).toList();
    }

    public Optional<CategoryResponse> getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId).map(this::mapToCategoryResponse);
    }

    public Optional<CategoryResponse> updateCategory(String categoryId, CategoryRequest categoryRequest) {
        return categoryRepository.findById(categoryId).map(category -> {
            category.setName(categoryRequest.name());
            category.setDescription(categoryRequest.description());
            category.setSubCategories(categoryRequest.subCategories());
            categoryRepository.save(category);
            return mapToCategoryResponse(category);
        });
    }

    public Optional<CategoryResponse> partialUpdateCategory(String categoryId, CategoryRequest categoryRequest) {
        return categoryRepository.findById(categoryId).map(category -> {
            if (categoryRequest.name() != null) category.setName(categoryRequest.name());
            if (categoryRequest.description() != null) category.setDescription(categoryRequest.description());
            if (categoryRequest.subCategories() != null) category.setSubCategories(categoryRequest.subCategories());
            categoryRepository.save(category);
            return mapToCategoryResponse(category);
        });
    }

    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<CategoryResponse> getProductsByCategoryId(String categoryId) {
        List<Product> products = productRepository.findByCategory(categoryId);
        return products.stream()
                .map(product -> new CategoryResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        List.of() // Assuming subCategories are not relevant here
                ))
                .toList();
    }

    public List<CategoryResponse> bulkCreateOrUpdateCategories(List<CategoryRequest> categoryRequests) {
        List<Category> categories = categoryRequests.stream().map(request -> Category.builder()
                .name(request.name())
                .description(request.description())
                .subCategories(request.subCategories())
                .build()).toList();
        categoryRepository.saveAll(categories);
        return categories.stream().map(this::mapToCategoryResponse).toList();
    }

    public List<CategoryResponse> getCategoryTree() {
        List<Category> allCategories = categoryRepository.findAll();
        Map<String, CategoryResponse> categoryMap = allCategories.stream()
                .collect(Collectors.toMap(Category::getId, this::mapToCategoryResponse));

        List<CategoryResponse> rootCategories = new ArrayList<>();

        for (Category category : allCategories) {
            CategoryResponse categoryResponse = categoryMap.get(category.getId());
            if (category.getSubCategories() == null || category.getSubCategories().isEmpty()) {
                rootCategories.add(categoryResponse);
            } else {
                for (String subCategoryId : category.getSubCategories()) {
                    CategoryResponse subCategoryResponse = categoryMap.get(subCategoryId);
                    if (subCategoryResponse != null) {
                        categoryResponse.subCategories().add(String.valueOf(subCategoryResponse));
                    }
                }
            }
        }

        return rootCategories;
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription(), category.getSubCategories());
    }
}