package com.example.Novameuble.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin/test")
    public String adminAccess() {
        return "Bienvenue ADMIN 👑";
    }

    @GetMapping("/seller/test")
    public String sellerAccess() {
        return "Bienvenue SELLER 🛠️";
    }

    @GetMapping("/client/test")
    public String clientAccess() {
        return "Bienvenue CLIENT 🛒";
    }
}

