package com.agency.platform.repository;

import com.agency.platform.model.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeadRepository extends MongoRepository<Lead, String> {
}
