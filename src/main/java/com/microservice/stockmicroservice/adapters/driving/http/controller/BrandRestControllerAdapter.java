package com.microservice.stockmicroservice.adapters.driving.http.controller;

import com.microservice.stockmicroservice.adapters.driving.http.dto.request.AddBrandRequest;
import com.microservice.stockmicroservice.adapters.driving.http.mapper.IBrandRequestMapper;
import com.microservice.stockmicroservice.domain.api.IBrandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandRestControllerAdapter {
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;

    @PostMapping("/")
    public ResponseEntity<Void> createBrand(@RequestBody AddBrandRequest brandRequest){
        brandServicePort.create(brandRequestMapper.addRequestToModel(brandRequest));
        return ResponseEntity.status(HttpStatus.CREATED ).build();
    }

}
