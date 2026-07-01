package com.agency.platform.service;

import com.agency.platform.model.Quotation;
import com.agency.platform.repository.QuotationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;

    public QuotationService(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    public Quotation calculateAndSave(String clientName, String clientEmail, String projectType, List<String> modules, int usersCount, String urgency) {
        double basePrice = switch (projectType.toLowerCase()) {
            case "web" -> 1200.0;
            case "app" -> 1800.0;
            case "erp" -> 2500.0;
            default -> 2000.0;
        };

        double modulesPrice = (modules != null ? modules.size() : 0) * 300.0;
        double usersPrice = usersCount > 50 ? 500.0 : (usersCount > 10 ? 200.0 : 0.0);

        double total = basePrice + modulesPrice + usersPrice;

        double multiplier = switch (urgency.toLowerCase()) {
            case "high" -> 1.25;
            case "critical" -> 1.5;
            default -> 1.0;
        };

        double finalEstimate = total * multiplier;

        Quotation quotation = Quotation.builder()
            .clientName(clientName)
            .clientEmail(clientEmail)
            .projectType(projectType)
            .modules(modules)
            .usersCount(usersCount)
            .urgency(urgency)
            .priceEstimate(finalEstimate)
            .createdAt(LocalDateTime.now())
            .status("PENDING")
            .build();

        return quotationRepository.save(quotation);
    }

    public List<Quotation> getAllQuotations() {
        return quotationRepository.findAll();
    }
}
