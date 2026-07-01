package com.agency.platform.service;

import com.agency.platform.model.Blog;
import com.agency.platform.repository.BlogRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @PostConstruct
    public void seedBlogs() {
        if (blogRepository.findAll().isEmpty()) {
            Blog post1 = Blog.builder()
                .title("Por qué elegimos Spring Boot y Java 21 para Aplicaciones Empresariales")
                .slug("spring-boot-java-21-empresarial")
                .summary("Analizamos el impacto de los Virtual Threads en el rendimiento y escalabilidad de servicios REST modernos.")
                .content("<p>El ecosistema de Java ha experimentado una de sus mayores revoluciones con la llegada de los hilos virtuales (Virtual Threads) en Java 21. Tradicionalmente, cada hilo en Java correspondía a un hilo del sistema operativo, limitando la escalabilidad debido al alto consumo de memoria y CPU.</p><p>Con Spring Boot y Java 21, podemos gestionar millones de conexiones concurrentes de forma eficiente y segura, ideal para procesar simuladores, APIs complejas y paneles de control empresariales.</p>")
                .category("Backend")
                .tags(List.of("Java 21", "Spring Boot", "Escalabilidad"))
                .author("Arquitecto Senior")
                .createdAt(LocalDateTime.now().minusDays(2))
                .build();

            Blog post2 = Blog.builder()
                .title("Estrategias de Diseño UI/UX: Inspiración en Apple, Stripe y Linear")
                .slug("diseno-ui-ux-apple-stripe-linear")
                .summary("Cómo crear interfaces oscuras con glassmorphism, partículas y efectos hover interactivos de alta gama.")
                .content("<p>El diseño moderno no se trata solo de la funcionalidad, sino de la emoción del usuario al interactuar. Inspirándonos en líderes como Stripe y Linear, creamos interfaces oscuras profundas utilizando variables de CSS embebidas para cambiar de tema instantáneamente.</p><p>A través de animaciones fluidas basadas en CSS puro y micro-transiciones, logramos capturar la atención del cliente y transmitir profesionalismo desde el primer segundo.</p>")
                .category("UI/UX")
                .tags(List.of("Aesthetics", "Glassmorphism", "CSS"))
                .author("Diseñador UX Principal")
                .createdAt(LocalDateTime.now().minusDays(5))
                .build();

            blogRepository.save(post1);
            blogRepository.save(post2);
        }
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogBySlug(String slug) {
        return blogRepository.findBySlug(slug);
    }
}
