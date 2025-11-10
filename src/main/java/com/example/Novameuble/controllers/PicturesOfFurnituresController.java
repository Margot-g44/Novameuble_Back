package com.example.Novameuble.controllers;

import com.example.Novameuble.entities.PicturesOfFurnitures;
import com.example.Novameuble.services.PicturesOfFurnituresService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class PicturesOfFurnituresController {

    private final PicturesOfFurnituresService service;

    public PicturesOfFurnituresController(PicturesOfFurnituresService service) {
        this.service = service;
    }

    @GetMapping
    public List<PicturesOfFurnitures> getAllPictures() {
        return service.getAllPictures();
    }

    @GetMapping("/{id}")
    public PicturesOfFurnitures getPictureById(@PathVariable Long id) {
        return service.getPictureById(id)
                .orElseThrow(() -> new RuntimeException("Image non trouvée"));
    }

    @PostMapping
    public PicturesOfFurnitures createPicture(@RequestBody PicturesOfFurnitures picture) {
        return service.createPicture(picture);
    }

    @PutMapping("/{id}")
    public PicturesOfFurnitures updatePicture(@PathVariable Long id, @RequestBody PicturesOfFurnitures picture) {
        return service.updatePicture(id, picture);
    }

    @DeleteMapping("/{id}")
    public void deletePicture(@PathVariable Long id) {
        service.deletePicture(id);
    }
}

