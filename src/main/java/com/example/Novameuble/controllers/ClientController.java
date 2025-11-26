package com.example.Novameuble.controllers;

import com.example.Novameuble.Security.CustomUserDetails;
import com.example.Novameuble.entities.Users;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@RestController
@RequestMapping("/client")
@PreAuthorize("hasAnyRole('CLIENT','SELLER','ADMIN')")

public class ClientController {

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/profile")
    public Users getClientProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userDetails.getUser();
    }

}