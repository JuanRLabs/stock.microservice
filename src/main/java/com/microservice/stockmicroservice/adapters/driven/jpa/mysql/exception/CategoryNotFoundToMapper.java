package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception;

public class CategoryNotFoundToMapper extends RuntimeException{
    public CategoryNotFoundToMapper(String message) {
        super(message);
    }
}
