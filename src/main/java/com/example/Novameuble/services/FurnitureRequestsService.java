package com.example.Novameuble.services;

import com.example.Novameuble.entities.FurnitureRequests;
import com.example.Novameuble.repositories.FurnitureRequestsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FurnitureRequestsService {

    private final FurnitureRequestsRepository repository;

    public FurnitureRequestsService(FurnitureRequestsRepository repository) {
        this.repository = repository;
    }

    public List<FurnitureRequests> getAllRequests() {
        return repository.findAll();
    }

    public FurnitureRequests getRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public FurnitureRequests saveRequest(FurnitureRequests request) {
        request.setStatus("en_attente");       // statut initial automatique
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        return repository.save(request);
    }

    public FurnitureRequests updateRequest(Long id, FurnitureRequests requestDetails) {
        FurnitureRequests existing = repository.findById(id).orElse(null);
        if (existing != null) {


            if (requestDetails.getStatus() != null) {
                existing.setStatus(requestDetails.getStatus());
            }

            existing.setUpdatedAt(LocalDateTime.now());

            return repository.save(existing);
        }
        return null;
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
