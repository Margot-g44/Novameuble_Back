package com.example.Novameuble.controllers;

import com.example.Novameuble.Security.CustomUserDetails;
import com.example.Novameuble.entities.Users;
import com.example.Novameuble.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Users user) {
        try {
            Users savedUser = authService.register(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            String token = authService.login(user.getEmail(), user.getPassword());
            Users loggedUser = authService.findByEmail(user.getEmail());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", loggedUser.getRole().name(),
                    "userId", loggedUser.getId()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(
            @RequestBody com.example.Novameuble.dto.PasswordCheckRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Users user = userDetails.getUser();
        boolean isValid = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        return ResponseEntity.ok(isValid);
    }
}
