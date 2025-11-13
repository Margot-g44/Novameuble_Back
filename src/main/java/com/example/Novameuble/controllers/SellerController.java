package com.example.Novameuble.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @GetMapping("/meubles")
    public String getSellerPage() {
        return "Page du vendeur : ici, seuls les SELLER peuvent accéder !";
    }
}
