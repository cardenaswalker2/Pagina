package com.agency.platform.service;

import com.agency.platform.model.Project;
import com.agency.platform.repository.ProjectRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostConstruct
    public void seedProjects() {
        if (projectRepository.findAll().isEmpty()) {
            List<Project.Milestone> milestones = new ArrayList<>();
            milestones.add(Project.Milestone.builder().title("Reunión Inicial y Requerimientos").date("2026-06-01").completed(true).build());
            milestones.add(Project.Milestone.builder().title("Diseño UI/UX (Figma)").date("2026-06-10").completed(true).build());
            milestones.add(Project.Milestone.builder().title("Desarrollo de API & Base de Datos").date("2026-06-20").completed(true).build());
            milestones.add(Project.Milestone.builder().title("Integración Flutter Web/Móvil").date("2026-07-05").completed(false).build());
            milestones.add(Project.Milestone.builder().title("Pruebas y Despliegue Cloud").date("2026-07-15").completed(false).build());

            Project project = Project.builder()
                .name("Sistema de Gestión Veterinaria Premium")
                .clientEmail("client@client.com")
                .status("IN_PROGRESS")
                .progress(60)
                .description("Plataforma empresarial de administración clínica, agenda veterinaria interactiva, portal del paciente y sistema de facturación electrónica integrado.")
                .price(4200.0)
                .timeline(milestones)
                .documents(List.of("Propuesta_Tecnica.pdf", "Contrato_Servicios.pdf", "Diseno_Arquitectura.png"))
                .build();

            projectRepository.save(project);
        }
    }

    public List<Project> getProjectsByClient(String email) {
        return projectRepository.findByClientEmail(email);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
}
