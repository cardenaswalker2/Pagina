package com.agency.platform.service;

import com.agency.platform.model.Lead;
import com.agency.platform.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead saveLead(String name, String email, String phone, String interest, String message) {
        Lead lead = Lead.builder()
            .name(name)
            .email(email)
            .phone(phone)
            .interest(interest)
            .message(message)
            .createdAt(LocalDateTime.now())
            .build();
        return leadRepository.save(lead);
    }

    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }
}
