package com.jijonj.microservices.product.repository;

import com.jijonj.microservices.product.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagRepository extends MongoRepository<Tag, String> {
    List<Tag> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}