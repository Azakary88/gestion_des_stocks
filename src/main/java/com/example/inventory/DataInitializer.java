package com.example.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.inventory.service.AuthService;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.entity.Product;
import com.example.inventory.entity.AppUser;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(
            AuthService authService,
            ProductRepository productRepository
    ) {
        return args -> {

            // Création admin (idempotent)
            authService.register(
                "admin@azakary.com",
                "admin123",
                AppUser.Role.ADMIN
            );
            System.out.println("Admin user initialized");

            // Normalisation des produits existants
            productRepository.findAll().forEach(product -> {
                if (product.getPrice() <= 0) {
                    product.setPrice(0.0); // valeur neutre
                    productRepository.save(product);
                }
            });

            System.out.println("Products price normalization done");
        };
    }
}
