package com.microservice.StockMicroservice.domain.enums;

import org.springframework.http.HttpStatus;

public enum APIError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "There are attributes with wrong values"),

    BAD_FORMAT(HttpStatus.BAD_REQUEST, "The request not have a correct form"),

    CATEGORY_WITH_SAME_NAME(HttpStatus.BAD_REQUEST, "There is a category with the same name"),

    ILLEGAL_PARAMS_REQUEST(HttpStatus.BAD_REQUEST, "The request params not have a correct format");

    private final HttpStatus status;
    private final String message;

    APIError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
