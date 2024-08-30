package com.microservice.stockmicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class BrandResponse {
    private  Long id;
    private  String name;
    private  String description;

    public BrandResponse() {
    }
}
