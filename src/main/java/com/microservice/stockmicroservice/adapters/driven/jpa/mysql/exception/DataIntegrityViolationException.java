package com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception;

public class DataIntegrityViolationException  extends RuntimeException{
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
