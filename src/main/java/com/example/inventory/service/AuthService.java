package com.example.inventory.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.inventory.entity.AppUser;
import com.example.inventory.repository.UserRepository;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public AppUser register(String email, String password, AppUser.Role role) {

        // ✅ IDPOTENCE : ne pas recréer si existe déjà
        return repo.findByEmail(email).orElseGet(() -> {
            AppUser u = new AppUser();
            u.setEmail(email);
            u.setPasswordHash(encoder.encode(password));
            u.setRole(role);
            return repo.save(u);
        });
    }

    public AppUser authenticate(String email, String password) {
        AppUser u = repo.findByEmail(email).orElse(null);
        if (u == null) return null;
        if (!encoder.matches(password, u.getPasswordHash())) return null;
        return u;
    }
}
