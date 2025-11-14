package com.example.Novameuble.services;

import com.example.Novameuble.Security.CustomUserDetails;
import com.example.Novameuble.entities.Role;
import com.example.Novameuble.entities.Users;
import com.example.Novameuble.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Novameuble.Security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users register(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CLIENT);
        Users savedUser = usersRepository.save(user);



        savedUser.setPassword(null);

        return savedUser;

    }

    public String login(String email, String password) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        // Création d’un objet UserDetails standard de Spring Security
        org.springframework.security.core.userdetails.UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().name()) // Ex: ADMIN, SELLER, CLIENT
                        .build();

        // Génération du token JWT
        return jwtUtil.generateToken(userDetails);
    }

}
