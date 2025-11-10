package com.example.Novameuble.services;

import com.example.Novameuble.entities.FurnitureRequests;
import com.example.Novameuble.repositories.FurnitureRequestsRepository;
import org.springframework.stereotype.Service;
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
        return repository.save(request);
    }

    public FurnitureRequests updateRequest(Long id, FurnitureRequests requestDetails) {
        FurnitureRequests existing = repository.findById(id).orElse(null);
        if (existing != null) {
            existing.setFurnitureId(requestDetails.getFurnitureId());
            existing.setSeller(requestDetails.getSeller());
            existing.setStatus(requestDetails.getStatus());
            existing.setUpdatedAt(requestDetails.getUpdatedAt());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
