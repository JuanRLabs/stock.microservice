package com.microservice.StockMicroservice.domain.exceptions;

import com.microservice.StockMicroservice.domain.enums.APIError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class EmazonExceptions extends RuntimeException {

    private HttpStatus status;
    private String description;
    private List<String> reasons ;

    public EmazonExceptions(APIError error) {
        this.status = error.getStatus() ;
        this.description = error.getMessage() ;
    }

    public EmazonExceptions(HttpStatus status, String description, List<String> reasons) {
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getReasons() {
        return reasons;
    }
}
