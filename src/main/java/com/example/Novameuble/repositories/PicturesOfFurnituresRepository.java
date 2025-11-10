package com.example.Novameuble.repositories;

import com.example.Novameuble.entities.PicturesOfFurnitures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicturesOfFurnituresRepository extends JpaRepository<PicturesOfFurnitures, Long> {
}

