package com.microservice.stockmicroservice.domain.model;

import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.util.DomainConstants;

public class Brand {
    private Long id;
    private String name;
    private String description;

    public Brand() {
    }

    public Brand(Long id) {
        this.id = id;
    }

    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = name.trim();
        this.description = description.trim();
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
        if (name == null || name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_ILLEGAL_ARGUMENT_MESSAGE);
        }
        this.name = name.trim();
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_ILLEGAL_ARGUMENT_MESSAGE);
        }
        this.description = description.trim();
    }
}




