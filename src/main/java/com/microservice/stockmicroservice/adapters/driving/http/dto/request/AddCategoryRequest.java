package com.microservice.stockmicroservice.adapters.driving.http.dto.request;

public class AddCategoryRequest {
    private  String name;
    private  String description;

    public AddCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
