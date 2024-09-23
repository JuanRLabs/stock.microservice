package com.microservice.stockmicroservice.domain.model;

import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.util.DomainConstants;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private Brand brand;
    private List<Category> categoriesId;

    public Product() {
    }

    public Product(Long id, String name, String description, Long quantity, BigDecimal price, Brand brand, List<Category> categoriesId) {
        if (name == null || name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_ILLEGAL_ARGUMENT_MESSAGE);
        }
        if (description == null || description.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_ILLEGAL_ARGUMENT_MESSAGE);
        }
        if (quantity == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_QUANTITY_ILLEGAL_ARGUMENT_MESSAGE);
        }
        if (price == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_PRICE_ILLEGAL_ARGUMENT_MESSAGE);
        }
        if (brand == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_BRAND_ILLEGAL_ARGUMENT_MESSAGE);
        }
        if (categoriesId == null || categoriesId.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_CATEGORIESID_ILLEGAL_ARGUMENT_MESSAGE);
        }
        if ( categoriesId.size() > 3) {
                throw new EmptyFieldException(DomainConstants.FIELD_CATEGORIESID_MAXIMUM_ILLEGAL_ARGUMENT_MESSAGE);
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

    public List<Category> getCategoriesId() {
        return categoriesId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_ILLEGAL_ARGUMENT_MESSAGE);
        } this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_ILLEGAL_ARGUMENT_MESSAGE);
        }
        this.description = description;
    }

    public void setQuantity(Long quantity) {
        if (quantity == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_QUANTITY_ILLEGAL_ARGUMENT_MESSAGE);
        }
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        if (price == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_PRICE_ILLEGAL_ARGUMENT_MESSAGE);
        }
        this.price = price;
    }

    public void setBrand(Brand brand) {
        if (brand == null) {
            throw new EmptyFieldException(DomainConstants.FIELD_BRAND_ILLEGAL_ARGUMENT_MESSAGE);
        }
        this.brand = brand;
    }

    public void setCategoriesId(List<Category> categoriesId) {
        if (categoriesId == null || categoriesId.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_CATEGORIESID_ILLEGAL_ARGUMENT_MESSAGE);
        }
        if ( categoriesId.size() > 3) {
            throw new EmptyFieldException(DomainConstants.FIELD_CATEGORIESID_MAXIMUM_ILLEGAL_ARGUMENT_MESSAGE);
        }
        this.categoriesId = categoriesId;
    }
}
