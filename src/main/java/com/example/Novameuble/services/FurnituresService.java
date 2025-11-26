package com.example.Novameuble.services;

import com.example.Novameuble.entities.Furnitures;
import com.example.Novameuble.entities.Users;
import com.example.Novameuble.repositories.FurnituresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FurnituresService {

    private final FurnituresRepository furnituresRepository;

    public FurnituresService(FurnituresRepository furnituresRepository) {
        this.furnituresRepository = furnituresRepository;
    }


    public List<Furnitures> getAll() {
        return furnituresRepository.findAll();
    }

    public List<Furnitures> getValidatedFurnitures() {
        return furnituresRepository.findByStatus("valide");
    }


    public Furnitures getById(Long id) {
        return furnituresRepository.findById(id).orElse(null);
    }


    public Furnitures create(Furnitures furniture) {
        // Le seller est déjà associé via le controller
        return furnituresRepository.save(furniture);
    }

    public void deleteBySeller(Long id, Users seller) {
        Furnitures furniture = furnituresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meuble introuvable"));

        if (!furniture.getSeller().getId().equals(seller.getId())) {
            throw new RuntimeException("Accès interdit : vous n'êtes pas le propriétaire de ce meuble");
        }

        furnituresRepository.delete(furniture);
    }


    public Furnitures updateByAdmin(Long id, Furnitures furnitureDetails) {
        Furnitures existing = furnituresRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meuble introuvable"));

        existing.setSeller(furnitureDetails.getSeller());
        existing.setName(furnitureDetails.getName());
        existing.setType(furnitureDetails.getType());
        existing.setMaterial(furnitureDetails.getMaterial());
        existing.setColor(furnitureDetails.getColor());
        existing.setPrice(furnitureDetails.getPrice());
        existing.setStatus(furnitureDetails.getStatus());

        return furnituresRepository.save(existing);
    }

    public List<Furnitures> getByStatus(String status) {
        return furnituresRepository.findByStatus(status);
    }


    public List<Furnitures> getAllFurnitures() {
        return furnituresRepository.findAll();
    }
}
