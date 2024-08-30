package com.microservice.stockmicroservice.domain.model;

public class Brand {
    private Long id;
    private String name;
    private String description;

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
        this.name = name.trim();
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }
}




