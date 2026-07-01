package com.agency.platform.repository;

import com.agency.platform.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByClientEmail(String clientEmail);
}
