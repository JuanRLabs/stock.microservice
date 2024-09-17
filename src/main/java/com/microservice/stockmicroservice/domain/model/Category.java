package com.microservice.stockmicroservice.domain.model;

import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.util.DomainConstants;

import java.util.Objects;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_IS_EMPTY_MESSAGE);
        }
        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_IS_EMPTY_MESSAGE);
        }
        this.id = id;
        this.name = name;
        this.description = description;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}


