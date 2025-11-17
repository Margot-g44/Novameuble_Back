package com.example.Novameuble.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@PreAuthorize("hasAnyRole('CLIENT','SELLER','ADMIN')")

public class ClientController {

    @GetMapping("/profile")
    public String getClientProfile() {
        return "Bienvenue sur ton profil client !";
    }
}

