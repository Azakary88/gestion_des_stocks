package com.example.inventory.service;

import com.example.inventory.entity.AppUser;
import com.example.inventory.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public void register(String email, String password, AppUser.Role role) {
        AppUser u = new AppUser();
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(password));
        u.setRole(role);
        repo.save(u);
    }


    public AppUser authenticate(String email, String password) {
        AppUser u = repo.findByEmail(email).orElse(null);
        if (u == null) return null;

        if (!encoder.matches(password, u.getPasswordHash())) return null;

        return u;
    }
}
