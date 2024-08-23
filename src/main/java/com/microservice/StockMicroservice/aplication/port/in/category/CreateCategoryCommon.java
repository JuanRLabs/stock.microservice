package com.microservice.StockMicroservice.aplication.port.in.category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryCommon  {
    private String name;
    private String description;
}