package com.agency.platform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "quotations")
public class Quotation {
    @Id
    private String id;
    private String clientName;
    private String clientEmail;
    private String projectType; // Web, App, ERP, etc.
    private List<String> modules;
    private int usersCount;
    private String urgency; // Normal, High, Critical
    private double priceEstimate;
    private LocalDateTime createdAt;
    private String status; // PENDING, APPROVED, CONTACTED
}
