package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception;

public class DataNotFoundToMapper extends RuntimeException{
    public DataNotFoundToMapper(String message) {
        super(message);
    }
}
