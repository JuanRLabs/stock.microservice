package com.microservice.stockmicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private BrandResponse brand;
    //ignorar el description de categoryResponse
    private List<CategoryResponse> categoriesId;

}
