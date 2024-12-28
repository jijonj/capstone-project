package com.jijonj.microservices.product.controller;

import com.jijonj.microservices.product.dto.TagRequest;
import com.jijonj.microservices.product.dto.TagResponse;
import com.jijonj.microservices.product.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponse createTag(@RequestBody TagRequest tagRequest) {
        return tagService.createTag(tagRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponse> getAllTags(@RequestParam int page, @RequestParam int size) {
        return tagService.getAllTags(page, size);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponse> searchTags(@RequestParam String query) {
        return tagService.searchTags(query);
    }

    @GetMapping("/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TagResponse> getTagById(@PathVariable String tagId) {
        return tagService.getTagById(tagId);
    }

    @PutMapping("/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TagResponse> updateTag(@PathVariable String tagId, @RequestBody TagRequest tagRequest) {
        return tagService.updateTag(tagId, tagRequest);
    }

    @PatchMapping("/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TagResponse> partialUpdateTag(@PathVariable String tagId, @RequestBody TagRequest tagRequest) {
        return tagService.partialUpdateTag(tagId, tagRequest);
    }

    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable String tagId) {
        tagService.deleteTag(tagId);
    }

    @GetMapping("/products/{productId}/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponse> getTagsByProductId(@PathVariable String productId) {
        return tagService.getTagsByProductId(productId);
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<TagResponse> bulkCreateOrUpdateTags(@RequestBody List<TagRequest> tagRequests) {
        return tagService.bulkCreateOrUpdateTags(tagRequests);
    }

    @GetMapping("/{tagId}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponse> getProductsByTagId(@PathVariable String tagId) {
        return tagService.getProductsByTagId(tagId);
    }
}