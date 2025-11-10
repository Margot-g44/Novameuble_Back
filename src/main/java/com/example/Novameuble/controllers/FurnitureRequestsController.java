package com.example.Novameuble.controllers;

import com.example.Novameuble.entities.FurnitureRequests;
import com.example.Novameuble.services.FurnitureRequestsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class FurnitureRequestsController {

    private final FurnitureRequestsService service;

    public FurnitureRequestsController(FurnitureRequestsService service) {
        this.service = service;
    }

    @GetMapping
    public List<FurnitureRequests> getAllRequests() {
        return service.getAllRequests();
    }

    @GetMapping("/{id}")
    public FurnitureRequests getRequestById(@PathVariable Long id) {
        return service.getRequestById(id);
    }

    @PostMapping
    public FurnitureRequests createRequest(@RequestBody FurnitureRequests request) {
        return service.saveRequest(request);
    }

    @PutMapping("/{id}")
    public FurnitureRequests updateRequest(@PathVariable Long id, @RequestBody FurnitureRequests request) {
        return service.updateRequest(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        service.deleteRequest(id);
    }
}

