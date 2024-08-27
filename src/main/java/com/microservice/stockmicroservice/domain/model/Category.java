package com.microservice.stockmicroservice.domain.model;

import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.utilityClass.DomainConstants;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return this.description;}

    public void setDescription(String description) {
        this.description = description;
    }
}


