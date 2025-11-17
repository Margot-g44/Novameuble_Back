package com.example.Novameuble.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.Novameuble.entities.Furnitures;
import com.example.Novameuble.services.FurnituresService;

@RestController
@RequestMapping("/api/furnitures")
public class FurnituresController {

    @Autowired
    private FurnituresService service;

    @GetMapping
    public List<Furnitures> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Furnitures getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping
    public Furnitures create(@RequestBody Furnitures furniture) {
        return service.create(furniture);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Furnitures update(@PathVariable Long id, @RequestBody Furnitures furniture) {
        return service.update(id, furniture);
    }

    @PreAuthorize("hasRole('SELLER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}