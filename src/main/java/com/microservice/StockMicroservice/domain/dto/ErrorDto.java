package com.microservice.StockMicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ErrorDto {

    private String description;

    private List<String> reasons;

    public ErrorDto(String description, List<String> reasons) {
        this.description = description;
        this.reasons = reasons;
    }
}
