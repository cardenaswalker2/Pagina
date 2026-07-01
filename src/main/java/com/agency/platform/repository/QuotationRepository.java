package com.agency.platform.repository;

import com.agency.platform.model.Quotation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface QuotationRepository extends MongoRepository<Quotation, String> {
    List<Quotation> findByClientEmail(String clientEmail);
}
