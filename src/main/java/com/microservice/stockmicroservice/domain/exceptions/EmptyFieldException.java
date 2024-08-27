package com.microservice.stockmicroservice.domain.exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String message) {super(message);}
}
