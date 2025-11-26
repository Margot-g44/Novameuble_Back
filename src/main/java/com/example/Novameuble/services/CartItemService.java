package com.example.Novameuble.services;

import com.example.Novameuble.Security.SecurityUtil;
import com.example.Novameuble.entities.CartItem;
import com.example.Novameuble.entities.Carts;
import com.example.Novameuble.entities.Users;
import com.example.Novameuble.repositories.CartItemRepository;
import com.example.Novameuble.repositories.CartsRepository;
import com.example.Novameuble.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final CartsRepository cartsRepository;

    public CartItemService(CartItemRepository cartItemRepository,
                           UserRepository userRepository,
                           CartsRepository cartsRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.cartsRepository = cartsRepository;
    }

    private Users getAuthenticatedUser() {
        String email = SecurityUtil.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé."));
    }

    private Carts getUserCart(Users user) {
        return cartsRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Panier non trouvé pour cet utilisateur."));
    }

    private void checkOwnership(CartItem item, Users user) {
        if (!item.getCart().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Accès interdit : ce CartItem n'appartient pas à cet utilisateur.");
        }
    }

    public List<CartItem> getAllCartItems() {
        Users user = getAuthenticatedUser();
        Carts cart = getUserCart(user);
        return cartItemRepository.findByCartId(cart.getId());
    }

    public Optional<CartItem> getCartItemById(Long id) {
        Users user = getAuthenticatedUser();

        Optional<CartItem> itemOpt = cartItemRepository.findById(id);
        itemOpt.ifPresent(item -> checkOwnership(item, user));

        return itemOpt;
    }

    public CartItem createCartItem(CartItem cartItem) {
        Users user = getAuthenticatedUser();
        Carts cart = getUserCart(user);

        cartItem.setCart(cart);  // on assigne le panier de l'utilisateur
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(Long id, CartItem updatedCartItem) {
        Users user = getAuthenticatedUser();

        CartItem existing = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem introuvable"));

        checkOwnership(existing, user);

        existing.setQuantity(updatedCartItem.getQuantity());
        existing.setFurniture(updatedCartItem.getFurniture());

        return cartItemRepository.save(existing);
    }

    public void deleteCartItem(Long id) {
        Users user = getAuthenticatedUser();

        CartItem existing = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem introuvable"));

        checkOwnership(existing, user);

        cartItemRepository.deleteById(id);
    }
}
