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
    private Long brandId;
    private List<Long> categoriesId;

    public Product(Long id, String name, String description, Long quantity, BigDecimal price, Long brandId, List<Long> categoriesId) {
            if (name == null || name.isEmpty()) {
                throw new EmptyFieldException("El nombre no puede ser nulo o vacío");
            }
            if (description == null || description.isEmpty()) {
                throw new EmptyFieldException("La descripción no puede ser nula o vacía");
            }
            if (quantity == null) {
                throw new EmptyFieldException("La cantidad no puede ser nula");
            }
            if (price == null) {
                throw new EmptyFieldException("El precio no puede ser nulo");
            }
            if (brandId == null) {
                throw new EmptyFieldException("El id de la marca no puede ser nulo");
            }
            if (categoriesId == null || categoriesId.isEmpty()) {
                throw new EmptyFieldException("La lista de categorías no puede ser nula o vacía");
            }

        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brandId = brandId;
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

    public Long getBrandId() {
        return brandId;
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

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public void setCategories(List<Long> categoriesId) {
        this.categoriesId = categoriesId;
    }
}
