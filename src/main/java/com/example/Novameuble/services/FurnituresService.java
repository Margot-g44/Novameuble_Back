package com.example.Novameuble.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.Novameuble.entities.Furnitures;
import com.example.Novameuble.repositories.FurnituresRepository;

@Service
public class FurnituresService {

    @Autowired
    private FurnituresRepository repository;

    public List<Furnitures> getAll() {
        return repository.findAll();
    }

    public Furnitures getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Furniture not found"));
    }

    public Furnitures create(Furnitures furniture) {
        return repository.save(furniture);
    }

    public Furnitures update(Long id, Furnitures updatedFurniture) {
        return repository.findById(id).map(f -> {
            f.setName(updatedFurniture.getName());
            f.setType(updatedFurniture.getType());
            f.setMaterial(updatedFurniture.getMaterial());
            f.setColor(updatedFurniture.getColor());
            f.setPrice(updatedFurniture.getPrice());
            f.setStatus(updatedFurniture.getStatus());
            f.setUpdatedAt(java.time.LocalDateTime.now());
            return repository.save(f);
        }).orElseThrow(() -> new RuntimeException("Furniture not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
