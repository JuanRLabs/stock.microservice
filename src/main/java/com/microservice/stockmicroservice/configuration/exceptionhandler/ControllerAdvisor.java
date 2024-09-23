package com.microservice.stockmicroservice.configuration.exceptionhandler;

import com.microservice.stockmicroservice.domain.exceptions.*;
import com.microservice.stockmicroservice.configuration.Constants;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentException;
import com.microservice.stockmicroservice.domain.util.DomainConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(){
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleProductAlreadyExistsException(){
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.PRODUCT_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionResponse> handlerIllegalArgumentException(IllegalArgumentException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(String.format(exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBrandAlreadyExistsException(){
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler({IllegalArgumentNameException.class})
    public ResponseEntity<ExceptionResponse> handlerIllegalArgumentNameException(){
        return ResponseEntity.badRequest().body(new ExceptionResponse(DomainConstants.FIELD_NAME_NULL_OR_ILLEGAL_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler({IllegalArgumentDescriptionException.class})
    public ResponseEntity<ExceptionResponse> handlerIllegalArgumentDescriptionException(){
        return ResponseEntity.badRequest().body(new ExceptionResponse(DomainConstants.FIELD_DESCRIPTION_NULL_OR_ILLEGAL_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler({IllegalBrandException.class})
    public ResponseEntity<ExceptionResponse> handlerIllegalBrandException(){
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.NO_BRAND_FOUND_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

}
