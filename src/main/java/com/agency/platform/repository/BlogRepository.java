package com.agency.platform.repository;

import com.agency.platform.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {
    Optional<Blog> findBySlug(String slug);
}
