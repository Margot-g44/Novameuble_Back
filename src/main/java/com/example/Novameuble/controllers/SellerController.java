package com.example.Novameuble.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
@PreAuthorize("hasRole('SELLER')")
public class SellerController {

    @GetMapping("/meubles")
    public String getSellerPage() {
        return "Page du vendeur : ici, seuls les vendeurs peuvent y accéder !";
    }
}
