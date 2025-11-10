package com.example.Novameuble.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "furniture_requests")
public class FurnitureRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "furniture_id", nullable = false)
    private Long furnitureId;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Users seller;

    @Column(nullable = false)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFurnitureId() { return furnitureId; }
    public void setFurnitureId(Long furnitureId) { this.furnitureId = furnitureId; }

    public Users getSeller() { return seller; }
    public void setSeller(Users seller) { this.seller = seller; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
