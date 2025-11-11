package com.example.Novameuble.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Carts cart;

    @ManyToOne
    @JoinColumn(name = "furniture_id", nullable = false)
    private Furnitures furniture;

    private Integer quantity = 1;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Carts getCart() { return cart; }
    public void setCart(Carts cart) { this.cart = cart; }

    public Furnitures getFurniture() { return furniture; }
    public void setFurniture(Furnitures furniture) { this.furniture = furniture; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
