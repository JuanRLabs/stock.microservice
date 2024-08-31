package com.microservice.stockmicroservice.adapters.driving.http.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class AddProductRequest {
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private Long brandId;
    private List<Long> categoriesId;

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
