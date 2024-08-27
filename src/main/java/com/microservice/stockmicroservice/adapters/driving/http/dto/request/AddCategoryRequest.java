package com.microservice.stockmicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddCategoryRequest {
    private  String name;
    private  String description;
}
