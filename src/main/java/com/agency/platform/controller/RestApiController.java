package com.agency.platform.controller;

import com.agency.platform.model.Lead;
import com.agency.platform.model.Quotation;
import com.agency.platform.service.LeadService;
import com.agency.platform.service.QuotationService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class RestApiController {

    private final QuotationService quotationService;
    private final LeadService leadService;

    public RestApiController(QuotationService quotationService, LeadService leadService) {
        this.quotationService = quotationService;
        this.leadService = leadService;
    }

    @PostMapping("/cotizacion")
    public ResponseEntity<Quotation> createQuotation(@RequestBody QuotationRequest request) {
        Quotation quotation = quotationService.calculateAndSave(
            request.getClientName(),
            request.getClientEmail(),
            request.getProjectType(),
            request.getModules(),
            request.getUsersCount(),
            request.getUrgency()
        );
        return ResponseEntity.ok(quotation);
    }

    @PostMapping("/lead")
    public ResponseEntity<Lead> createLead(@RequestBody LeadRequest request) {
        Lead lead = leadService.saveLead(
            request.getName(),
            request.getEmail(),
            request.getPhone(),
            request.getInterest(),
            request.getMessage()
        );
        return ResponseEntity.ok(lead);
    }

    @PostMapping("/ai-chat")
    public ResponseEntity<Map<String, String>> aiChat(@RequestBody Map<String, String> request) {
        String message = request.getOrDefault("message", "").toLowerCase();
        String response;

        if (message.contains("precio") || message.contains("costo") || message.contains("cotiz")) {
            response = "¡Excelente pregunta! Para darte el mejor precio a tu medida, te sugiero usar nuestra **Calculadora de Costos** en el menú superior. Al finalizar, podrás enviarme tu configuración directamente a mi **WhatsApp** personal para pasarte un presupuesto formal.";
        } else if (message.contains("hola") || message.contains("buenos") || message.contains("tardes")) {
            response = "¡Hola! Bienvenido al estudio de desarrollo de **Luis Devp**. ¿Qué tipo de software o app móvil te gustaría diseñar hoy? Cuéntame tu idea.";
        } else if (message.contains("tiempo") || message.contains("plazo") || message.contains("demora")) {
            response = "El tiempo de desarrollo varía: una Landing Page premium toma 1-2 semanas, un sistema a medida (ERP/CRM) entre 6-10 semanas, y apps móviles nativas con Flutter entre 8-12 semanas.";
        } else if (message.contains("veterinari") || message.contains("restaurante") || message.contains("clinica") || message.contains("hospital")) {
            response = "¡Tengo simuladores totalmente interactivos listos para esos sistemas! Puedes verlos y probarlos en la sección **Simuladores** del menú superior.";
        } else if (message.contains("whatsapp") || message.contains("correo") || message.contains("contacto")) {
            response = "Claro que sí, puedes contactarme directamente al WhatsApp o rellenar el formulario de contacto para que conversemos sobre tu proyecto.";
        } else {
            response = "En **Luis Devp** construyo software a medida de nivel premium, como Web Apps, Apps en Flutter, integraciones API y dashboards de administración. ¿Qué funcionalidad específica necesitas?";
        }

        return ResponseEntity.ok(Map.of("response", response));
    }

    @Data
    public static class QuotationRequest {
        private String clientName;
        private String clientEmail;
        private String projectType;
        private List<String> modules;
        private int usersCount;
        private String urgency;
    }

    @Data
    public static class LeadRequest {
        private String name;
        private String email;
        private String phone;
        private String interest;
        private String message;
    }
}
