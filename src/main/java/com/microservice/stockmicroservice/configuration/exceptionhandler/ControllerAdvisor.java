package com.microservice.stockmicroservice.configuration.exceptionhandler;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.microservice.stockmicroservice.configuration.Constants;
import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.utilityClass.DomainConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(DomainConstants.FIELD_NAME_OR_DESCRIPTION_NULL_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(){
        return ResponseEntity.badRequest().body(new ExceptionResponse(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }


}
