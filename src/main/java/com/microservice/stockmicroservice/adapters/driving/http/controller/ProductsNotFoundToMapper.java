package com.microservice.stockmicroservice.adapters.driving.http.controller;

public class ProductsNotFoundToMapper extends RuntimeException {
    public ProductsNotFoundToMapper(String message) {
        super(message);
    }
}
