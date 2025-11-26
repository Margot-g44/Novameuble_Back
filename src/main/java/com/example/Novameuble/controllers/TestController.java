package com.example.Novameuble.controllers;

import com.example.Novameuble.entities.Users;
import com.example.Novameuble.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TestController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminAccess() {
        return "Bienvenue ADMIN 👑";
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/seller")
    public String sellerAccess() {
        return "Bienvenue SELLER 🛠️";
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/client")
    public String clientAccess() {
        return "Bienvenue CLIENT 🛒";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/verify-password")
    public String verifyPassword(@RequestBody com.example.Novameuble.dto.PasswordCheckRequest request) {
        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (matches) {
            return "Le mot de passe correspond au hash !";
        } else {
            return "Le mot de passe ne correspond pas au hash.";
        }
    }
}
