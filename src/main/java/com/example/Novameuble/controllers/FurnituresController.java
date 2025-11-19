package com.example.Novameuble.controllers;

import com.example.Novameuble.Security.CustomUserDetails;
import com.example.Novameuble.entities.Furnitures;
import com.example.Novameuble.services.FurnituresService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/furnitures")
@CrossOrigin(origins = "*")
public class FurnituresController {

    private final FurnituresService service;

    public FurnituresController(FurnituresService service) {
        this.service = service;
    }

    @GetMapping
    public List<Furnitures> getAllFurnitures() {
        return service.getAllFurnitures();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/validated")
    public List<Furnitures> getValidatedFurniture() {
        return service.getByStatus("valide");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Furnitures> getById(@PathVariable Long id) {
        Furnitures furniture = service.getById(id);
        if (furniture == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(furniture);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping
    public ResponseEntity<Furnitures> create(@RequestBody Furnitures furniture,
                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        furniture.setSeller(userDetails.getUser());
        Furnitures savedFurniture = service.create(furniture);
        return ResponseEntity.ok(savedFurniture);
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        try {
            service.deleteBySeller(id, userDetails.getUser());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).build(); // Accès interdit si pas le vendeur propriétaire
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Furnitures> update(@PathVariable Long id,
                                             @RequestBody Furnitures furnitureDetails) {
        try {
            Furnitures updated = service.updateByAdmin(id, furnitureDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
