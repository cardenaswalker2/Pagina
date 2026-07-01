package com.agency.platform.service;

import com.agency.platform.model.User;
import com.agency.platform.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seedUsers() {
        // Crear administrador por defecto si no existe
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                .username("admin")
                .email("admin@agency.com")
                .password(passwordEncoder.encode("admin123"))
                .role("ROLE_ADMIN")
                .build();
            userRepository.save(admin);
        }

        // Crear cliente de prueba por defecto si no existe
        if (userRepository.findByUsername("client").isEmpty()) {
            User client = User.builder()
                .username("client")
                .email("client@client.com")
                .password(passwordEncoder.encode("client123"))
                .role("ROLE_CLIENT")
                .build();
            userRepository.save(client);
        }

        // Crear cliente demo de prueba por defecto si no existe
        if (userRepository.findByUsername("demo").isEmpty()) {
            User demo = User.builder()
                .username("demo")
                .email("demo@demo.com")
                .password(passwordEncoder.encode("demo123"))
                .role("ROLE_CLIENT")
                .build();
            userRepository.save(demo);
        }
    }

    public User registerUser(String username, String email, String password, String role) {
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("El usuario o email ya existe.");
        }

        User user = User.builder()
            .username(username)
            .email(email)
            .password(passwordEncoder.encode(password))
            .role(role)
            .build();

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
