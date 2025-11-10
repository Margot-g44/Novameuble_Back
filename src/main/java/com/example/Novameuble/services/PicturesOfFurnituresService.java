package com.example.Novameuble.services;

import com.example.Novameuble.entities.PicturesOfFurnitures;
import com.example.Novameuble.repositories.PicturesOfFurnituresRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PicturesOfFurnituresService {

    private final PicturesOfFurnituresRepository repository;

    public PicturesOfFurnituresService(PicturesOfFurnituresRepository repository) {
        this.repository = repository;
    }

    public List<PicturesOfFurnitures> getAllPictures() {
        return repository.findAll();
    }

    public Optional<PicturesOfFurnitures> getPictureById(Long id) {
        return repository.findById(id);
    }

    public PicturesOfFurnitures createPicture(PicturesOfFurnitures picture) {
        return repository.save(picture);
    }

    public PicturesOfFurnitures updatePicture(Long id, PicturesOfFurnitures updatedPicture) {
        return repository.findById(id).map(picture -> {
            picture.setUrl(updatedPicture.getUrl());
            picture.setFurniture(updatedPicture.getFurniture());
            return repository.save(picture);
        }).orElseThrow(() -> new RuntimeException("Image non trouvée"));
    }

    public void deletePicture(Long id) {
        repository.deleteById(id);
    }
}
