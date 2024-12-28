package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.CategoryRequest;
import com.jijonj.microservices.product.dto.CategoryResponse;
import com.jijonj.microservices.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories(@RequestParam int page, @RequestParam int size) {
        return categoryService.getAllCategories(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> searchCategories(@RequestParam String query) {
        return categoryService.searchCategories(query);
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CategoryResponse> getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CategoryResponse> updateCategory(@PathVariable String categoryId, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryId, categoryRequest);
    }

    @PatchMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CategoryResponse> partialUpdateCategory(@PathVariable String categoryId, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.partialUpdateCategory(categoryId, categoryRequest);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/{categoryId}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getProductsByCategoryId(@PathVariable String categoryId) {
        return categoryService.getProductsByCategoryId(categoryId);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CategoryResponse> bulkCreateOrUpdateCategories(@RequestBody List<CategoryRequest> categoryRequests) {
        return categoryService.bulkCreateOrUpdateCategories(categoryRequests);
    }

    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getCategoryTree() {
        return categoryService.getCategoryTree();
    }
}