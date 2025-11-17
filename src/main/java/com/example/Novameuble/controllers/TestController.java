package com.example.Novameuble.controllers;

import com.example.Novameuble.entities.Users;
import com.example.Novameuble.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/test")
    public String adminAccess() {
        return "Bienvenue ADMIN 👑";
    }

    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/seller/test")
    public String sellerAccess() {
        return "Bienvenue SELLER 🛠️";
    }

    @GetMapping("/client/test")
    public String clientAccess() {
        return "Bienvenue CLIENT 🛒";
    }

    @PostMapping("/verify-password")
    public String verifyPassword(@RequestBody PasswordCheckRequest request) {
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
