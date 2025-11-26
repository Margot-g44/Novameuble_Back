package com.example.Novameuble.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pictures_of_furnitures")
public class PicturesOfFurnitures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "furniture_id", nullable = false)
    @JsonIgnore
    private Furnitures furniture;

    @Column(nullable = false)
    private String url;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public PicturesOfFurnitures() {}

    public PicturesOfFurnitures(Furnitures furniture, String url) {
        this.furniture = furniture;
        this.url = url;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Furnitures getFurniture() {
        return furniture;
    }

    public void setFurniture(Furnitures furniture) {
        this.furniture = furniture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
