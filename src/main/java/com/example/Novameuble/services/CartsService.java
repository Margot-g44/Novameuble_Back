package com.example.Novameuble.services;

import com.example.Novameuble.entities.Carts;
import com.example.Novameuble.repositories.CartsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsService {

    private final CartsRepository cartRepository;

    public CartsService(CartsRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Carts> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Carts> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Carts createCart(Carts cart) {
        return cartRepository.save(cart);
    }

    public Carts updateCart(Long id, Carts cartDetails) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setUser(cartDetails.getUser());
                    cart.setCreatedAt(cartDetails.getCreatedAt());
                    return cartRepository.save(cart);
                })
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
