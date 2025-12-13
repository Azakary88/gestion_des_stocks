// src/main/java/com/example/inventory/entity/Product.java
package com.example.inventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    @NotBlank
    private String sku;

    @Column(nullable=false)
    @NotBlank
    private String name;

    private String description;

    private int stockQuantity;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Supplier supplier;

    @Column(nullable=false)
    private double price;

    public Product() {}

    public Long getId() { return id; }
    public void setId(Long id){ this.id=id; }

    public String getSku(){ return sku; }
    public void setSku(String sku){ this.sku=sku; }

    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }

    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price=price; }

    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description=description; }

    public int getStockQuantity(){ return stockQuantity; }
    public void setStockQuantity(int q){ this.stockQuantity=q; }

    public Category getCategory(){ return category; }
    public void setCategory(Category c){ this.category=c; }
    public Supplier getSupplier(){ return supplier; }
    public void setSupplier(Supplier s){ this.supplier=s; }
}
