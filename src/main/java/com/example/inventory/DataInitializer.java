package com.example.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.inventory.service.AuthService;
import com.example.inventory.entity.AppUser;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(AuthService authService) {
        return args -> {

            authService.register(
                "admin@azakary.com",
                "admin123",
                AppUser.Role.ADMIN
            );

            System.out.println("Admin user initialized (idempotent)");
        };
    }
}
