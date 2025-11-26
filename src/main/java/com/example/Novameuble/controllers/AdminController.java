package com.example.Novameuble.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")

public class AdminController {

    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Bienvenue dans le panneau d'administration ! (accès réservé à l'ADMIN)";
    }
}
