package com.example.inventory.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.entity.AppUser;
import com.example.inventory.entity.AppUser.Role;
import com.example.inventory.service.UserService;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppUser> list() {
        return service.list();
    }

    @PostMapping
    public AppUser create(@RequestBody Map<String, String> body) {
        return service.create(
            body.get("email"),
            body.get("password"),
            Role.valueOf(body.get("role"))
        );
    }

    @PutMapping("/{id}")
    public AppUser update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.update(id, Role.valueOf(body.get("role")));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
