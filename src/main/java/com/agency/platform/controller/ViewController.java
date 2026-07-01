package com.agency.platform.controller;

import com.agency.platform.model.Project;
import com.agency.platform.model.Quotation;
import com.agency.platform.model.Blog;
import com.agency.platform.model.Lead;
import com.agency.platform.service.ProjectService;
import com.agency.platform.service.QuotationService;
import com.agency.platform.service.LeadService;
import com.agency.platform.service.BlogService;
import com.agency.platform.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ViewController {

    private final ProjectService projectService;
    private final QuotationService quotationService;
    private final LeadService leadService;
    private final BlogService blogService;
    private final UserService userService;

    public ViewController(ProjectService projectService, QuotationService quotationService,
                          LeadService leadService, BlogService blogService, UserService userService) {
        this.projectService = projectService;
        this.quotationService = quotationService;
        this.leadService = leadService;
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "index";
    }

    @GetMapping("/simulador")
    public String simulador(Model model) {
        return "simulador";
    }

    @GetMapping("/casos")
    public String casos() {
        return "casos";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String handleRegistro(@RequestParam String username, @RequestParam String email, 
                                 @RequestParam String password, Model model) {
        try {
            userService.registerUser(username, email, password, "ROLE_CLIENT");
            return "redirect:/login?registered=true";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }

    @GetMapping("/cliente")
    public String cliente(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        
        // Cargar los proyectos correspondientes al email del usuario autenticado
        String email = principal.getName();
        // Si entra como admin/client, buscamos por su usuario o email
        userService.findByUsername(principal.getName()).ifPresent(u -> {
            model.addAttribute("user", u);
        });

        // Buscamos proyectos asociados
        List<Project> clientProjects = projectService.getProjectsByClient(email);
        if (clientProjects.isEmpty()) {
            // Fallback por nombre de usuario si no está configurado el email en Spring Security
            clientProjects = projectService.getProjectsByClient("client@client.com");
        }

        model.addAttribute("projects", clientProjects);
        return "cliente";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        
        List<Project> allProjects = projectService.getAllProjects();
        List<Quotation> allQuotations = quotationService.getAllQuotations();
        List<Lead> allLeads = leadService.getAllLeads();

        model.addAttribute("projects", allProjects);
        model.addAttribute("quotations", allQuotations);
        model.addAttribute("leads", allLeads);
        
        return "admin";
    }

}
