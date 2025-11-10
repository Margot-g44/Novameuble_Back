package com.example.Novameuble.repositories;

import com.example.Novameuble.entities.Furnitures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnituresRepository extends JpaRepository<Furnitures, Long> {
}

