package com.example.inventory.controller;

import com.example.inventory.entity.AppUser;
import com.example.inventory.security.JwtUtil;
import com.example.inventory.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService auth;
    private final JwtUtil jwt;

    public AuthController(AuthService auth, JwtUtil jwt) {
        this.auth = auth;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest req) {

        AppUser u = auth.authenticate(req.email, req.password);
        if (u == null) {
            return new ErrorResponse("INVALID_CREDENTIALS");
        }

        String token = jwt.generateToken(u.getEmail(), u.getRole().name()); // FIX : convertir ENUM → String

        return new LoginResponse(token, u.getEmail(), u.getRole().name());
    }

    public static record LoginRequest(String email, String password) {}
    public static record LoginResponse(String token, String email, String role) {}
    public static record ErrorResponse(String message) {}
}
