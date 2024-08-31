package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddProductRequest;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.IProductRequestMapper;
import com.microservice.stockmicroservice.domain.api.IProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductRestControllerAdapter {
    private final IProductServicePort productServicePort;
    private final IProductRequestMapper productRequestMapper;

    @PostMapping("/")
    public ResponseEntity<Void> createProduct(@RequestBody AddProductRequest request){
        productServicePort.create(productRequestMapper.addRequestToProduct(request));
        return ResponseEntity.status(HttpStatus.CREATED ).build();
    }



}
