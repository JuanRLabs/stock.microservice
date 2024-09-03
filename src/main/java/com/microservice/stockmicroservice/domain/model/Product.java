package com.microservice.stockmicroservice.domain.model;

import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private Brand brand;
    private List<Long> categoriesId;

    public Product() {
    }

    public Product(Long id, String name, String description, Long quantity, BigDecimal price, Brand brand, List<Long> categoriesId) {
            if (name == null || name.isEmpty()) {
                throw new EmptyFieldException("The name cannot be null or empty");
            }
            if (description == null || description.isEmpty()) {
                throw new EmptyFieldException("The description cannot be null or empty");
            }
            if (quantity == null) {
                throw new EmptyFieldException("The quantity cannot be null or empty");
            }
            if (price == null) {
                throw new EmptyFieldException("The price cannot be null or empty");
            }
            if (brand == null) {
                throw new EmptyFieldException("The brand cannot be null or empty");
            }
            if (categoriesId == null || categoriesId.isEmpty()) {
                throw new EmptyFieldException("The categoriesId list cannot be null or empty");
            }
            if (categoriesId.stream().distinct().count() != categoriesId.size()){
            throw new EmptyFieldException("The categoriesId list has not content duplicate elements");
            }
            if ( categoriesId.size() > 3) {
                throw new EmptyFieldException("The categoriesId list has not more than three elements");
            }

        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categoriesId = categoriesId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public List<Long> getCategoriesId() {
        return categoriesId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setCategories(List<Long> categoriesId) {
        this.categoriesId = categoriesId;
    }
}
