package com.microservice.stockmicroservice.adapters.driving.http.dto.request;

import lombok.Data;

@Data
public class AddBrandRequest {
    private String name;
    private String description;
}
