package com.example.Novameuble.services;


import com.example.Novameuble.entities.Users;
import com.example.Novameuble.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Users createUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Users updateUser(Long id, Users updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            user.setUpdatedAt(java.time.LocalDateTime.now());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}


