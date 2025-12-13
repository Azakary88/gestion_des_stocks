package com.example.inventory.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory.entity.AppUser;
import com.example.inventory.entity.AppUser.Role;
import com.example.inventory.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public List<AppUser> list() {
        return repo.findAll();
    }

    public AppUser create(String email, String password, Role role) {
        AppUser u = new AppUser();
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(password));
        u.setRole(role);
        return repo.save(u);
    }

    public AppUser update(Long id, Role role) {
        AppUser u = repo.findById(id).orElseThrow();
        u.setRole(role);
        return repo.save(u);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
