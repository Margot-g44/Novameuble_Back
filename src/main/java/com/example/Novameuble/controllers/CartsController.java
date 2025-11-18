package com.example.Novameuble.controllers;

import com.example.Novameuble.entities.Carts;
import com.example.Novameuble.services.CartsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartsController {

    private final CartsService cartService;

    public CartsController(CartsService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping
    public List<Carts> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<Carts> getCartById(@PathVariable Long id) {
        return cartService.getCartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public Carts createCart(@RequestBody Carts cart) {
        return cartService.createCart(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carts> updateCart(@PathVariable Long id, @RequestBody Carts cartDetails) {
        try {
            return ResponseEntity.ok(cartService.updateCart(id, cartDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('CLIENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
