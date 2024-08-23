package com.microservice.StockMicroservice.domain.exceptions;

import com.microservice.StockMicroservice.domain.dto.ErrorDto;
import com.microservice.StockMicroservice.domain.enums.APIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmazonExceptions.class)
    public ResponseEntity<ErrorDto> duplicadtedResource(EmazonExceptions e, WebRequest request){
        return ResponseEntity.status(e.getStatus())
                .body(new ErrorDto(e.getDescription(), e.getReasons()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request)
    {
        List<String> reasons = new ArrayList<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){
          reasons.add(String.format("%s  - %s", error.getField(), error.getDefaultMessage()));
        }
        return ResponseEntity.status(APIError.VALIDATION_ERROR.getStatus())
                .body(new ErrorDto(APIError.VALIDATION_ERROR.getMessage(), reasons ));
    }

}
