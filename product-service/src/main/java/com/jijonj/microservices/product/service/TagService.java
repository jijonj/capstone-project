package com.jijonj.microservices.product.service;

import com.jijonj.microservices.product.dto.TagRequest;
import com.jijonj.microservices.product.dto.TagResponse;
import com.jijonj.microservices.product.model.Tag;
import com.jijonj.microservices.product.repository.TagRepository;
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
public class TagService {
    private final TagRepository tagRepository;

    public TagResponse createTag(TagRequest tagRequest) {
        Tag tag = Tag.builder()
                .name(tagRequest.name())
                .description(tagRequest.description())
                .build();
        tagRepository.save(tag);
        log.info("Tag created successfully");
        return mapToTagResponse(tag);
    }

    public List<TagResponse> getAllTags(int page, int size) {
        Page<Tag> tags = tagRepository.findAll(PageRequest.of(page, size));
        return tags.stream().map(this::mapToTagResponse).toList();
    }

    public List<TagResponse> searchTags(String query) {
        List<Tag> tags = tagRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        return tags.stream().map(this::mapToTagResponse).toList();
    }

    public Optional<TagResponse> getTagById(String tagId) {
        return tagRepository.findById(tagId).map(this::mapToTagResponse);
    }

    public Optional<TagResponse> updateTag(String tagId, TagRequest tagRequest) {
        return tagRepository.findById(tagId).map(tag -> {
            tag.setName(tagRequest.name());
            tag.setDescription(tagRequest.description());
            tagRepository.save(tag);
            return mapToTagResponse(tag);
        });
    }

    public Optional<TagResponse> partialUpdateTag(String tagId, TagRequest tagRequest) {
        return tagRepository.findById(tagId).map(tag -> {
            if (tagRequest.name() != null) tag.setName(tagRequest.name());
            if (tagRequest.description() != null) tag.setDescription(tagRequest.description());
            tagRepository.save(tag);
            return mapToTagResponse(tag);
        });
    }

    public void deleteTag(String tagId) {
        tagRepository.deleteById(tagId);
    }

    public List<TagResponse> getTagsByProductId(String productId) {
        // Implement logic to retrieve tags by product ID
        return List.of(); // Placeholder
    }

    public List<TagResponse> bulkCreateOrUpdateTags(List<TagRequest> tagRequests) {
        List<Tag> tags = tagRequests.stream().map(request -> Tag.builder()
                .name(request.name())
                .description(request.description())
                .build()).toList();
        tagRepository.saveAll(tags);
        return tags.stream().map(this::mapToTagResponse).toList();
    }

    public List<TagResponse> getProductsByTagId(String tagId) {
        // Implement logic to retrieve products by tag ID
        return List.of(); // Placeholder
    }

    private TagResponse mapToTagResponse(Tag tag) {
        return new TagResponse(tag.getId(), tag.getName(), tag.getDescription());
    }
}